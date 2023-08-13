package com.mehmetpetek.themoviedbcompose.data.repository

import com.mehmetpetek.themoviedbcompose.data.local.db.TMDBDao
import com.mehmetpetek.themoviedbcompose.data.local.model.TMDBEntity
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBStorageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TMDBStorageRepositoryImpl @Inject constructor(
    private val tmdbDao: TMDBDao,
) : TMDBStorageRepository {

    override suspend fun insertMovies(movies: List<TMDBEntity>) {
        tmdbDao.insertMovies(movies)
    }

    override fun getMovies(page: Int, sortBy: String): Flow<List<TMDBEntity>> {
        return tmdbDao.getMovies(page, sortBy)
    }
}