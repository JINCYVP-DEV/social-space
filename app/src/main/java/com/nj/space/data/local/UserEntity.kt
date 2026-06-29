package com.nj.space.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
@Serializable
data class UserEntity(

    @get:PropertyName("id") @set:PropertyName("id")
    @PrimaryKey var id: String = "",


    @get:PropertyName("username") @set:PropertyName("username")
    var username: String? = null,


    @get:PropertyName("email") @set:PropertyName("email")
    var email: String = "",
    @get:PropertyName("displayName") @set:PropertyName("displayName")
    var displayName: String? = null,

    @get:PropertyName("pronouns") @set:PropertyName("pronouns")
    var pronouns: String? = null,

    @get:PropertyName("gender") @set:PropertyName("gender")
    var gender: String? = null,


    @get:PropertyName("isVerified") @set:PropertyName("isVerified")
    var isVerified: Boolean = false,
    @get:PropertyName("profilePictureUrl") @set:PropertyName("profilePictureUrl")
    var profilePictureUrl: String = "",

    @get:PropertyName("bio") @set:PropertyName("bio")
    var bio: String? = null,

    @get:PropertyName("followerCount") @set:PropertyName("followerCount")
    var followerCount: Int = 0,

    @get:PropertyName("followingCount") @set:PropertyName("followingCount")
    var followingCount: Int = 0,

    @get:PropertyName("postCount") @set:PropertyName("postCount")
    var postCount: Int = 0,

    @get:PropertyName("isFollowing") @set:PropertyName("isFollowing")
    var isFollowing: Boolean = false,

    @get:PropertyName("createdAt") @set:PropertyName("createdAt")
    var createdAt: Long = System.currentTimeMillis(),

    @get:PropertyName("updatedAt") @set:PropertyName("updatedAt")
    var updatedAt: Long = System.currentTimeMillis()
)





