package com.mehmetpetek.themoviedbcompose.app.di

import android.content.Context
import androidx.room.Room
import com.mehmetpetek.themoviedbcompose.BuildConfig
import com.mehmetpetek.themoviedbcompose.data.local.db.TMDBDatabase
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
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, TMDBDatabase::class.java, BuildConfig.APPLICATION_ID + ".db"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMovieDBDao(db: TMDBDatabase) = db.movieDBDao()
}