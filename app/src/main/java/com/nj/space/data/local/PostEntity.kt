package com.nj.space.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Entity(tableName = "posts",
    foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = ["id"],
    childColumns = ["userId"],
    onDelete = ForeignKey.CASCADE, // Deletes the posts if the user profile gets deleted
    deferred = true     )           // Delays verification until the database save finishes
])
    @Serializable
data class PostEntity(
    @get:PropertyName("id")
    @set:PropertyName("id")
    @PrimaryKey var id: String = "",

    @get:PropertyName("userId")
    @set:PropertyName("userId")
    var userId: String = "",

    @get:PropertyName("imageUrl")
    @set:PropertyName("imageUrl")
    var imageUrl: String = "",

    @get:PropertyName("caption")
    @set:PropertyName("caption")
    var caption: String = "",

    @get:PropertyName("likes")
    @set:PropertyName("likes")
    var likes: Int = 0,

    @get:PropertyName("comments")
    @set:PropertyName("comments")
    var comments: Int = 0,

    @get:PropertyName("isLiked")
    @set:PropertyName("isLiked")
    var isLiked: Boolean = false,

    @get:PropertyName("createdAt")
    @set:PropertyName("createdAt")
    var createdAt: Long = System.currentTimeMillis(),

    @get:PropertyName("updatedAt")
    @set:PropertyName("updatedAt")
    var updatedAt: Long = System.currentTimeMillis(),
) {
    @Ignore
    var author: UserEntity? = null
}

