package com.nj.instagram.domain.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.nj.instagram.data.db.dao.CommentDao
import com.nj.instagram.data.db.dao.LikeDao
import com.nj.instagram.data.db.dao.PostDao
import com.nj.instagram.data.local.CommentEntity
import com.nj.instagram.data.local.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostRepository{
   suspend fun loadFeedPosts(refresh: Boolean = false): List<PostEntity>
    fun getFeedPosts(): Flow<List<PostEntity>>
    suspend fun syncUserPosts()
    fun getUserPostsFromDao(userId: String): Flow<List<PostEntity>>
    suspend fun uploadPost(userId: String, imageUrl: String, caption: String): Result<String>
    suspend fun likePost(postId: String, userId: String): Result<Unit>
    suspend fun unlikePost(postId: String, userId: String): Result<Unit>
    suspend fun addComment(postId: String, userId: String, text: String): Result<String>
    fun getPostComments(postId: String): Flow<List<CommentEntity>>
    fun searchPosts(query: String): Flow<List<PostEntity>>

}
class PostRepositoryImpl  @Inject constructor(
    private val postDao: PostDao,
    private val commentDao: CommentDao,
    private val likeDao: LikeDao,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
):PostRepository {
    override suspend fun loadFeedPosts(refresh: Boolean): List<PostEntity> {
        TODO("Not yet implemented")
    }

    override fun getFeedPosts(): Flow<List<PostEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun syncUserPosts() {
        TODO("Not yet implemented")
    }

    override fun getUserPostsFromDao(userId: String): Flow<List<PostEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadPost(
        userId: String,
        imageUrl: String,
        caption: String
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun likePost(
        postId: String,
        userId: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun unlikePost(
        postId: String,
        userId: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addComment(
        postId: String,
        userId: String,
        text: String
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override fun getPostComments(postId: String): Flow<List<CommentEntity>> {
        TODO("Not yet implemented")
    }

    override fun searchPosts(query: String): Flow<List<PostEntity>> {
        TODO("Not yet implemented")
    }
}