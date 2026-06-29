package com.nj.space.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(entity = PostEntity::class, parentColumns = ["id"], childColumns = ["postId"]),
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["userId"])
    ]
)
data class CommentEntity(
    @PrimaryKey val id: String,
    val postId: String,
    val userId: String,
    val text: String,
    val createdAt: Long = System.currentTimeMillis()
) {
    @Ignore
    val author: UserEntity? = null
}
