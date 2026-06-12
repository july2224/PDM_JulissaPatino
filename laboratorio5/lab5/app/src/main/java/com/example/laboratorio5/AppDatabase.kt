package com.example.laboratorio5

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
