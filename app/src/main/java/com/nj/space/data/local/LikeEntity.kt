package com.nj.space.data.local

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "likes",
    primaryKeys = ["postId", "userId"],
    foreignKeys = [
        ForeignKey(entity = PostEntity::class, parentColumns = ["id"], childColumns = ["postId"]),
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["userId"])
    ]
)
data class LikeEntity(
    val postId: String,
    val userId: String,
    val createdAt: Long = System.currentTimeMillis()
)