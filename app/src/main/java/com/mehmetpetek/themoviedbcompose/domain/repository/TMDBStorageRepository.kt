package com.mehmetpetek.themoviedbcompose.domain.repository

import com.mehmetpetek.themoviedbcompose.data.local.model.TMDBEntity
import kotlinx.coroutines.flow.Flow

interface TMDBStorageRepository {
    suspend fun insertMovies(movies: List<TMDBEntity>)
    fun getMovies(page: Int, sortBy: String): Flow<List<TMDBEntity>>
}