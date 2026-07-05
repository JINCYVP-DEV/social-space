package com.nj.space.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.nj.space.data.db.dao.CommentDao
import com.nj.space.data.db.dao.FollowDao
import com.nj.space.data.db.dao.LikeDao
import com.nj.space.data.db.dao.PostDao
import com.nj.space.data.db.dao.UserDao
import com.nj.space.data.preferences.SessionManager
import com.nj.space.domain.repository.AuthRepository
import com.nj.space.domain.repository.PostRepository
import com.nj.space.domain.repository.PostRepositoryImpl
import com.nj.space.domain.repository.UserRepository
import com.nj.space.domain.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesPostRepository(postDao: PostDao,commentDao: CommentDao,likeDao: LikeDao, firebaseFirestore: FirebaseFirestore,firebaseStorage: FirebaseStorage,   @ApplicationContext context: Context ): PostRepository = PostRepositoryImpl(
        postDao = postDao,
        commentDao = commentDao,
        likeDao = likeDao,
        firebaseFirestore = firebaseFirestore,
        firebaseStorage = firebaseStorage,
        context = context,
    )

    @Provides
    @Singleton
    fun providesUserRepository(userDao: UserDao,followDao: FollowDao,firebaseFirestore: FirebaseFirestore): UserRepository =UserRepositoryImpl(
        userDao = userDao,
        followDao = followDao,
        firebaseFirestore = firebaseFirestore
    )


}