package com.nj.space.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nj.space.data.db.dao.CommentDao
import com.nj.space.data.db.dao.FollowDao
import com.nj.space.data.db.dao.LikeDao
import com.nj.space.data.db.dao.PostDao
import com.nj.space.data.db.dao.UserDao
import com.nj.space.data.preferences.SessionManager
import com.nj.space.domain.repository.AuthRepository
import com.nj.space.domain.repository.PostRepositoryImpl
import com.nj.space.domain.repository.UserRepositoryImpl
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

    @Provides
    @Singleton
    fun providesPostRepository(postDao: PostDao,commentDao: CommentDao,likeDao: LikeDao, firebaseFirestore: FirebaseFirestore): PostRepositoryImpl= PostRepositoryImpl(
        postDao = postDao,
        commentDao = commentDao,
        likeDao = likeDao,
        firebaseFirestore = firebaseFirestore
    )

    @Provides
    @Singleton
    fun providesUserRepository(userDao: UserDao,followDao: FollowDao,firebaseFirestore: FirebaseFirestore): UserRepositoryImpl =UserRepositoryImpl(
        userDao = userDao,
        followDao = followDao,
        firebaseFirestore = firebaseFirestore
    )




}