package com.nj.space.data.local

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "follows",
    primaryKeys = ["followerId", "followingId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["followerId"]
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["followingId"]
        )
    ]
)
data class FollowEntity(
    val followerId: String,
    val followingId: String,
    val createdAt: Long = System.currentTimeMillis()
)