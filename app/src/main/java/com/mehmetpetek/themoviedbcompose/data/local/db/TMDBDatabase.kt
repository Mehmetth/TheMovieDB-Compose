package com.mehmetpetek.themoviedbcompose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetpetek.themoviedbcompose.data.local.model.TMDBEntity

@Database(entities = [TMDBEntity::class], version = 1)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDBDao(): TMDBDao
}