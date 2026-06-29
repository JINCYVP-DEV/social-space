package com.nj.space.domain.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nj.space.data.db.dao.FollowDao
import com.nj.space.data.db.dao.UserDao
import com.nj.space.data.local.FollowEntity
import com.nj.space.data.local.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UserRepository {
    fun getUserProfile(userId: String): Flow<UserEntity?>
    suspend fun updateProfile(
        userId: String,
        username: String,
        displayName: String,
        gender: String?,
        bio: String,
        profilePictureUrl: String = ""
    ): Result<Unit>

    suspend fun followUser(followerId: String, followingId: String): Result<Unit>
    suspend fun unfollowUser(followerId: String, followingId: String): Result<Unit>
    fun searchUsers(query: String): Flow<List<UserEntity>>

}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val followDao: FollowDao,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {
    override fun getUserProfile(userId: String): Flow<UserEntity?> {
        return flow {
            try {
                val localUser= userDao.getUserById(userId)
                emit(localUser)
            } catch (e: Exception) {
                Log.e("UserRepo", e.message.toString())
            }
        }
    }

    override suspend fun updateProfile(
        userId: String,
        username: String,
        displayName: String,
        gender: String?,
        bio: String,
        profilePictureUrl: String
    ): Result<Unit> {
        return try {
            val updateMap= mutableMapOf<String,Any>(
                "username" to username,
                "bio" to bio,
                "updatedAt" to System.currentTimeMillis()
            )
            if (profilePictureUrl.isNotEmpty())
            {
                updateMap["profilePictureUrl"]=profilePictureUrl
            }
            firebaseFirestore.collection("users").document(userId).update(updateMap).await()
            val user = userDao.getUserById(userId)?.copy(username=username, bio = bio, profilePictureUrl = profilePictureUrl, displayName = displayName, gender = gender)
            user?.let {
                userDao.updateUser(user)
            }
            Result.success(Unit)
        }catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override suspend fun followUser(
        followerId: String, followingId: String
    ): Result<Unit> {
        return try {
            val follow = FollowEntity(followerId,followingId)
            followDao.followUser(follow)
            firebaseFirestore.collection("follows").add(
                mapOf("followerId" to followerId,
                    "followingId" to followingId,
                    "createdAt" to System.currentTimeMillis())
            ).await()
            Result.success(Unit)
        }catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override suspend fun unfollowUser(
        followerId: String, followingId: String
    ): Result<Unit> {
        return try {
            followDao.unfollowUser(followerId,followingId)
            val query = firebaseFirestore.collection("follows")
                .whereEqualTo("followerId" ,followerId)
                .whereEqualTo("followingId" ,followingId)
                .get().await()
            for (doc in query.documents ){
                firebaseFirestore.collection("follows")
                    .document(doc.id)
                    .delete().await()
            }
            Result.success(Unit)
        }catch (e: Exception)
        {
            Result.failure(e)
        }
    }

    override fun searchUsers(query: String): Flow<List<UserEntity>> = userDao.searchUsers(query)
}