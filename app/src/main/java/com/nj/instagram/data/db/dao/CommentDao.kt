package com.nj.instagram.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nj.instagram.data.local.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY createdAt DESC")
    fun getPostComments(postId: String): Flow<List<CommentEntity>>

    @Delete
    suspend fun deleteComment(comment: CommentEntity)
}