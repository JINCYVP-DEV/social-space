package com.nj.space.di

import android.content.Context
import androidx.room.Room
import com.nj.space.data.db.SocialSpaceDataBase
import com.nj.space.data.db.dao.CommentDao
import com.nj.space.data.db.dao.FollowDao
import com.nj.space.data.db.dao.LikeDao
import com.nj.space.data.db.dao.PostDao
import com.nj.space.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): SocialSpaceDataBase {
        return Room.databaseBuilder(context,SocialSpaceDataBase::class.java,"Instagram_db").build()
    }

    @Provides
    @Singleton
    fun providesUserDao(db: SocialSpaceDataBase): UserDao {
        return db.userDao()
    }


    @Provides
    @Singleton
    fun providePostDao(db: SocialSpaceDataBase) : PostDao{
        return db.postDao()
    }


    @Provides
    @Singleton
    fun providesCommentDao(db: SocialSpaceDataBase) : CommentDao{
        return  db.commentDao()
    }


    @Provides
    @Singleton
    fun providesFollowDao(db: SocialSpaceDataBase) : FollowDao {
        return db.followDao()
    }

    @Provides
    @Singleton
    fun providesLikesDao(db: SocialSpaceDataBase): LikeDao{
        return db.likeDao()
    }
}