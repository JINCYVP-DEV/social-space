package com.nj.space.util

import com.nj.space.data.local.PostEntity
import com.nj.space.data.local.UserEntity
import kotlin.collections.associateBy
import kotlin.collections.forEach

object DummyDataProvider {
    fun getDummyPosts(): List<PostEntity> {
        return listOf(
            PostEntity(
                id = "1",
                userId = "u1",
                imageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d",
                caption = "Enjoying the sunset 🌅",
                likes = 124,
                comments = 8,
                isLiked = false
            ),
            PostEntity(
                id = "2",
                userId = "u2",
                imageUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9",
                caption = "Nature vibes 🌿",
                likes = 540,
                comments = 23,
                isLiked = true
            ),
            PostEntity(
                id = "3",
                userId = "u3",
                imageUrl = "https://images.unsplash.com/photo-1541696432-82c6da8ce7bf",
                caption = "Coding and coffee ☕💻",
                likes = 302,
                comments = 19
            ),
            PostEntity(
                id = "4",
                userId = "u4",
                imageUrl = "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e",
                caption = "Weekend mood 😎",
                likes = 455,
                comments = 12
            ),
            PostEntity(
                id = "5",
                userId = "u5",
                imageUrl = "https://images.unsplash.com/photo-1506744038136-46273834b3fb",
                caption = "Exploring new places 🌍",
                likes = 788,
                comments = 44
            )
        )
    }

    val dummyUsers = listOf(
        UserEntity("u1", "Azeem", "azeem@example.com"),
        UserEntity("u2", "Fatima", "fatima@example.com"),
        UserEntity("u3", "Rizwan", "rizwan@example.com"),
        UserEntity("u4", "Sara", "sara@example.com"),
        UserEntity("u5", "Ali", "ali@example.com")
    )

    fun getPosts(): List<PostEntity> {
        val userMap = dummyUsers.associateBy { it.id }  // build lookup map once

        val posts = getDummyPosts()
        posts.forEach { post ->
            post.author = userMap[post.userId]
        }

        return posts
    }
}