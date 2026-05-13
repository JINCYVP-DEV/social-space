package com.nj.instagram.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.nj.instagram.data.db.dao.UserDao
import com.nj.instagram.data.preferences.SessionManager
import com.nj.instagram.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepository(
        firebaseAuth: FirebaseAuth,
        sessionManager: SessionManager,
        userDao: UserDao,
        firebaseFirestore: FirebaseFirestore
    ): AuthRepository = AuthRepository(
        firebaseAuth = firebaseAuth,
        sessionManager = sessionManager,
        userDao = userDao,
        firebaseFirestore = firebaseFirestore
    )





}