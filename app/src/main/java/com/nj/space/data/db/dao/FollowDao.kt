package com.nj.space.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nj.space.data.local.FollowEntity

@Dao
interface FollowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun followUser(follow: FollowEntity)

    @Query("DELETE FROM follows WHERE followerId = :followerId AND followingId = :followingId")
    suspend fun unfollowUser(followerId: String, followingId: String)

    @Query("SELECT COUNT(*) FROM follows WHERE followerId = :followerId AND followingId = :followingId")
    suspend fun isFollowing(followerId: String, followingId: String): Int
}
