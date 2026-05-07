package com.nj.instagram.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nj.instagram.data.db.dao.UserDao
import com.nj.instagram.data.local.UserEntity
import com.nj.instagram.data.db.dao.CommentDao
import com.nj.instagram.data.db.dao.FollowDao
import com.nj.instagram.data.db.dao.LikeDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class InstagramDataBase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun postDao(): UserDao
    abstract fun commentDao(): CommentDao
    abstract fun followDao(): FollowDao
    abstract fun likeDao(): LikeDao
}