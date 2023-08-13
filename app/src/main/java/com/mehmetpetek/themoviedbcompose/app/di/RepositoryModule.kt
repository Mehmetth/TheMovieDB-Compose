package com.mehmetpetek.themoviedbcompose.app.di

import com.mehmetpetek.themoviedbcompose.data.local.db.TMDBDao
import com.mehmetpetek.themoviedbcompose.data.remote.TMDBDataSource
import com.mehmetpetek.themoviedbcompose.data.remote.TMDBService
import com.mehmetpetek.themoviedbcompose.data.repository.TMDBRepositoryImpl
import com.mehmetpetek.themoviedbcompose.data.repository.TMDBStorageRepositoryImpl
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBRepository
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTMDBStorageRepository(
        tmdbDao: TMDBDao,
    ): TMDBStorageRepository = TMDBStorageRepositoryImpl(tmdbDao)

    @Provides
    @Singleton
    fun provideTMDBRepository(
        tmdbDataSource: TMDBDataSource,
        tmdbStorageRepository: TMDBStorageRepository
    ): TMDBRepository =
        TMDBRepositoryImpl(tmdbDataSource, tmdbStorageRepository)

    @Provides
    @Singleton
    fun provideTMDBDataSource(
        tmdbService: TMDBService,
    ): TMDBDataSource = TMDBDataSource(tmdbService)

}