package com.nj.space.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nj.space.data.local.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(list: List<PostEntity> )

    @Query("SELECT * FROM posts WHERE id= :postId")
    suspend fun getPostById(postId:String): PostEntity?

    @Query("SELECT * FROM posts ORDER BY createdAt DESC LIMIT :limit OFFSET :offset")
    fun getFeedPost(limit:Int=20,offset:Int=0): Flow<List<PostEntity>>


    @Query("SELECT * FROM posts WHERE userId = :userId ORDER BY createdAt DESC")
    fun getUserPosts(userId: String): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE caption LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchPosts(query: String): Flow<List<PostEntity>>

    @Update
    suspend fun updatePost(post: PostEntity)

    @Delete
    suspend fun deletePost(post: PostEntity)


    @Query("SELECT * FROM posts ORDER BY createdAt DESC")
    suspend fun getFeedPostsOnce(): List<PostEntity> // one-time fetch for sync

    @Query("DELETE FROM posts")
    suspend fun clearAll()
}