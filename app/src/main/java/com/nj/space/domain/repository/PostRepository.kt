package com.nj.space.domain.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storageMetadata
import com.nj.space.data.db.dao.CommentDao
import com.nj.space.data.db.dao.LikeDao
import com.nj.space.data.db.dao.PostDao
import com.nj.space.data.local.CommentEntity
import com.nj.space.data.local.LikeEntity
import com.nj.space.data.local.PostEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.UUID
import javax.inject.Inject

interface PostRepository {
    suspend fun loadFeedPosts(refresh: Boolean = false): List<PostEntity>
    fun getFeedPosts(): Flow<List<PostEntity>>
    suspend fun syncUserPosts(userId: String)
    fun getUserPostsFromDao(userId: String): Flow<List<PostEntity>>
    suspend fun uploadPost(userId: String, uri: Uri, caption: String, onProgress: (Int) -> Unit): Result<String>
    suspend fun likePost(postId: String, userId: String): Result<Unit>
    suspend fun unlikePost(postId: String, userId: String): Result<Unit>
    suspend fun addComment(postId: String, userId: String, text: String): Result<String>
    fun getPostComments(postId: String): Flow<List<CommentEntity>>
    fun searchPosts(query: String): Flow<List<PostEntity>>

}

class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val commentDao: CommentDao,
    private val likeDao: LikeDao,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
  @ApplicationContext private val context: Context
) : PostRepository {

    val pageSize = 10
    private var lastVisibleDoc: Any? = null

    override suspend fun loadFeedPosts(refresh: Boolean): List<PostEntity> {
        return try {
            //if refresh reset pagination
            if (refresh) lastVisibleDoc = null

            val query = firebaseFirestore.collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING).limit(pageSize.toLong())
            //if lastVisibleDoc exists start after that this line modifies the firebase query
            lastVisibleDoc?.let {
                query.startAfter(it)
            }

            //get from firebase
            val postSnapshot = query.get().await()
            val postFromFirebase = postSnapshot.documents.mapNotNull {doc->
                doc.toObject(PostEntity::class.java)?.copy(id = doc.id)
            }
            //update last visible doc for next pagination

            lastVisibleDoc= postSnapshot.documents.lastOrNull()

            //cache in room db
            if (refresh)postDao.clearAll()
            if (postFromFirebase.isNotEmpty())postDao.insertAllPosts(postFromFirebase)


            //return cached posts from  room
            postDao.getFeedPostsOnce()
        } catch (e: Exception) {
            //if firebase fails call the data from the room database
            println(e.message)
            postDao.getFeedPostsOnce()
        }
    }

    override fun getFeedPosts(): Flow<List<PostEntity>> = postDao.getFeedPost()
    override suspend fun syncUserPosts(userId: String) {
        try {
            val snapshot=firebaseFirestore.collection("posts")
                .whereEqualTo("userId",userId)
                .get()
                .await()

            val postFromFirebase= snapshot.documents.mapNotNull { doc->
                doc.toObject(PostEntity::class.java)?.copy(id = doc.id)//make sure firebase id is preserved
            }

            if (postFromFirebase.isNotEmpty())
            {
                postDao.insertAllPosts(postFromFirebase)
                Log.d("PostRepository","Synced ${postFromFirebase.size} posts for $userId")
            }
            else{
                Log.d("PostRepository","No posts found for use $userId")
            }
        }
        catch (e: Exception)
        {
            Log.e("PostRepository", "Error syncing posts: ${e.message}", e)
        }
    }

    override fun getUserPostsFromDao(userId: String): Flow<List<PostEntity>> = postDao.getUserPosts(userId)
    override suspend fun uploadPost(
        userId: String, uri: Uri,
        caption: String,
        onProgress: (Int) -> Unit

    ): Result<String> {
       return try {
           //upload into firebase cloud
//           val compressedImg= compressImage(uri)
//           val metadata = storageMetadata {
//               contentType = "image/jpeg"
//           }
//           val ref= firebaseStorage.reference.child("posts/${userId}/${UUID.randomUUID()}.jpg")
//           ref.putBytes(compressedImg,metadata)
//               .addOnProgressListener { task->
//                   val progress = ((100.0 * task.bytesTransferred) / task.totalByteCount).toInt()
//                   onProgress(progress)
//               }
//               .await()
          // val imageUrl= ref.downloadUrl.await().toString()
           val imageUrl= "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d"

           //upload into firebase db
           val postId= UUID.randomUUID().toString()
           val post= mapOf(
               "id" to postId,
               "userId" to userId,
               "imageUrl" to imageUrl,
               "caption" to caption,
               "likes" to 0,
               "comments" to 0,
               "createdAt" to System.currentTimeMillis()
           )
           firebaseFirestore.collection("posts").document(postId).set(post).await()
           postDao.insertPost(PostEntity(postId,userId,imageUrl,caption))
           Result.success(postId)
       }
       catch (e:Exception)
       {
           Result.failure(e)
       }
    }

    override suspend fun likePost(
        postId: String, userId: String
    ): Result<Unit> {
        return try {
            val like= LikeEntity(postId,userId)
            likeDao.likePost(like)
            val likeId="${userId}_$postId"
            firebaseFirestore.collection("likes").document(likeId)
                .set(mapOf(
                    "postId" to postId,
                    "userId" to userId,
                    "createdAt" to System.currentTimeMillis()
                )).await()
            val post = postDao.getPostById(postId)
            post?.let {
                postDao.updatePost(it.copy(likes =it.likes+1, isLiked = true ))
            }
            Result.success(Unit)
        }
        catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override suspend fun unlikePost(
        postId: String, userId: String
    ): Result<Unit> {
        return try {
            likeDao.unlikePost(postId,userId)
            val post=postDao.getPostById(postId)
            val likeId="${userId}_$postId"
            firebaseFirestore.collection("likes").document(likeId).delete().await()
            post?.let {
                if (it.likes>0)
                postDao.updatePost(it.copy(likes = it.likes-1, isLiked = false))
            }
            Result.success(Unit)
        }catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override suspend fun addComment(
        postId: String, userId: String, text: String
    ): Result<String> {
        return try {
            val commentId= UUID.randomUUID().toString()
            val comment= mapOf(
                "id" to commentId,
                "postId" to postId,
                "userId" to userId,
                "text" to text,
                "createdAt" to System.currentTimeMillis()
            )
            firebaseFirestore.collection("comments").document(commentId).set(comment).await()
            commentDao.insertComment(CommentEntity(commentId,postId,userId,text))
            Result.success(commentId)
        }catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override fun getPostComments(postId: String): Flow<List<CommentEntity>> =commentDao.getPostComments(postId)

    override fun searchPosts(query: String): Flow<List<PostEntity>> =postDao.searchPosts(query)
    private fun compressImage(uri: Uri): ByteArray
    {
        val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver,uri)
        return ByteArrayOutputStream().also { bitmap.compress(Bitmap.CompressFormat.JPEG,80,it) }.toByteArray()

    }
}