package com.nj.instagram.domain.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nj.instagram.data.db.UserDao
import com.nj.instagram.data.local.UserEntity
import com.nj.instagram.data.preferences.SessionManager
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sessionManager: SessionManager,
    private val userDao: UserDao,
    private val firebaseFirestore: FirebaseFirestore
) {

    suspend fun signUp(userName:String,password: String,email:String): Result<String> {

       return try {
           val authResult = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
           val userId = authResult.user?.uid ?: throw Exception("User creation failed")
           val userMap = mapOf(
               "id" to userId,
               "username" to userName,
               "email" to email,
               "profilePictureUrl" to "",
               "bio" to "",
               "followerCount" to 0,
               "followingCount" to 0,
               "postCount" to 0,
               "createdAt" to System.currentTimeMillis()
           )
           firebaseFirestore.collection("users").document(userId).set(userMap).await()
           userDao.insertUser(UserEntity(id = userId, username = userName, email = email))
           sessionManager.saveSession(userId, email, userName)
           Result.success(userId)
       } catch (e: Exception) {
           Log.e("AuthRepository", "SignUp error", e)
           Result.failure(e)
       }
    }
    suspend fun login(email: String, password: String): Result<String>
    {
       return  try {
            val authResult =firebaseAuth.signInWithEmailAndPassword(email,password).await()
             val userId= authResult.user?.uid ?: throw Exception("SignIn failed")
             val userDoc=firebaseFirestore.collection("users").document(userId).get().await()
            val user = userDoc.toObject(UserEntity::class.java) ?: throw Exception("User not found!")
           userDao.insertUser(user)
           sessionManager.saveSession(userId, email, user.username.orEmpty())
             Result.success(userId)
        }catch (e: Exception)
        {
            Log.e("AuthRepository", "Login error", e)
            Result.failure(e)
        }
    }

    suspend fun logout(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            sessionManager.clearSession()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCurrentUserId(): String? = firebaseAuth.currentUser?.uid
}