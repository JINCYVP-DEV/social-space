package com.nj.instagram.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nj.instagram.data.local.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class InstagramDataBase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun postDao(): UserDao
}