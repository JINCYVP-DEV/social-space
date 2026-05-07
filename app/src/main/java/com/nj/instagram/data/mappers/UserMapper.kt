package com.nj.instagram.data.mappers

import com.nj.instagram.data.local.UserEntity
import com.nj.instagram.data.remote.UserDto
import com.nj.instagram.domain.model.UserUiModel

fun UserDto.toEntity(): UserEntity= UserEntity(
    id = id,
    username = username,
    email = email,
    displayName = displayName,
    pronouns = pronouns,
    gender = gender,
    isVerified = isVerified,
    profilePictureUrl = profilePictureUrl,
    bio = bio,
    followerCount = followerCount,
    followingCount = followingCount,
    postCount = postCount,
    isFollowing = isFollowing,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserEntity.toUserDto(): UserDto= UserDto(
    id=id,
    username = username,
    email = email,
    displayName = displayName,
    pronouns = pronouns,
    gender = gender,
    isVerified = isVerified,
    profilePictureUrl = profilePictureUrl,
    bio = bio,
    followerCount = followerCount,
    followingCount = followingCount,
    postCount = postCount,
    isFollowing = isFollowing,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserEntity.toUiModel(): UserUiModel= UserUiModel(
    id = id,
    username = username ?: "",
    displayName = displayName ?: "",
    profilePictureUrl = profilePictureUrl,
    bio = bio ?: "",
    isVerified = isVerified
)