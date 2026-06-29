package com.nj.space.di

import android.content.Context
import androidx.room.Room
import com.nj.space.data.db.SocialSpaceDataBase
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
    fun providesUserDao(db: SocialSpaceDataBase) =db.userDao()


    @Provides
    @Singleton
    fun providePostDao(db: SocialSpaceDataBase) = db.postDao()


    @Provides
    @Singleton
    fun providesCommentDao(db: SocialSpaceDataBase) = db.commentDao()


    @Provides
    @Singleton
    fun providesFollowDao(db: SocialSpaceDataBase)=db.followDao()

    @Provides
    @Singleton
    fun providesLikesDao(db: SocialSpaceDataBase)=db.likeDao()
}