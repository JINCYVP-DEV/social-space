package com.nj.instagram.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nj.instagram.data.local.LikeEntity

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likePost(like: LikeEntity)

    @Query("DELETE FROM likes WHERE postId = :postId AND userId = :userId")
    suspend fun unlikePost(postId: String, userId: String)

    @Query("SELECT COUNT(*) FROM likes WHERE postId = :postId AND userId = :userId")
    suspend fun isLiked(postId: String, userId: String): Int
}