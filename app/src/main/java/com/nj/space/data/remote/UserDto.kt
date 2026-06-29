package com.nj.space.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String = "",
    val username: String? = null,
    val email: String = "",
    val displayName: String? = null,
    val pronouns: String? = null,
    val gender: String? = null,
    val isVerified: Boolean = false,
    val profilePictureUrl: String = "",
    val bio: String? = null,
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val postCount: Int = 0,
    val isFollowing: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isProUser: Boolean = false // future-proof field
)