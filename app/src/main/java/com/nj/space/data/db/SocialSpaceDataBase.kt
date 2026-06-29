package com.nj.space.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nj.space.data.db.dao.UserDao
import com.nj.space.data.local.UserEntity
import com.nj.space.data.db.dao.CommentDao
import com.nj.space.data.db.dao.FollowDao
import com.nj.space.data.db.dao.LikeDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class SocialSpaceDataBase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun postDao(): UserDao
    abstract fun commentDao(): CommentDao
    abstract fun followDao(): FollowDao
    abstract fun likeDao(): LikeDao
}