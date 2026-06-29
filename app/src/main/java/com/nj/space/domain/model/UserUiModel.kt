package com.nj.space.domain.model

data class UserUiModel(
    val id: String,
    val username: String,
    val displayName: String,
    val profilePictureUrl: String,
    val bio: String,
    val isVerified: Boolean
)