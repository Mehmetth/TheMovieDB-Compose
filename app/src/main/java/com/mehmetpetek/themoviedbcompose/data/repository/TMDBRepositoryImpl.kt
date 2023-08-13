package com.mehmetpetek.themoviedbcompose.data.repository

import com.mehmetpetek.themoviedbcompose.data.mapper.entitiesToMovie
import com.mehmetpetek.themoviedbcompose.data.mapper.movieToEntities
import com.mehmetpetek.themoviedbcompose.data.remote.TMDBDataSource
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieDetailResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieImageResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.Resource
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBRepository
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBStorageRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.net.UnknownHostException
import javax.inject.Inject

class TMDBRepositoryImpl @Inject constructor(
    private val tmdbDataSource: TMDBDataSource,
    private val tmdbStorageRepository: TMDBStorageRepository
) : TMDBRepository {
    override fun getDiscoverMovies(page: Int, sortBy: String): Flow<Resource<MovieResponse>> =
        callbackFlow {
            tmdbStorageRepository.getMovies(page, sortBy).collect {
                if (it.isNotEmpty()) {
                    trySend(
                        Resource.Success(
                            MovieResponse(
                                page = page,
                                total_pages = it.first().totalPage,
                                results = it.movieToEntities()
                            )
                        )
                    )
                } else {
                    try {
                        val response = tmdbDataSource.getDiscoverMovies(page, sortBy)

                        if (response.isSuccessful) {
                            response.body()?.let {
                                tmdbStorageRepository.insertMovies(
                                    it.results.entitiesToMovie(
                                        page,
                                        sortBy,
                                        totalPage = it.total_pages
                                    )
                                )
                                trySend(Resource.Success(it))
                            } ?: kotlin.run {
                                trySend(Resource.Fail(null))
                            }
                        } else {
                            trySend(Resource.Error(null))
                        }
                    } catch (ex: Exception) {
                        trySend(Resource.Error(UnknownHostException()))
                    }
                }
            }
            awaitClose { cancel() }
        }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetailResponse>> = callbackFlow {
        try {
            val response = tmdbDataSource.getMovieDetail(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    trySend(Resource.Success(it))
                } ?: kotlin.run {
                    trySend(Resource.Fail(null))
                }
            } else {
                trySend(Resource.Error(null))
            }
        } catch (ex: Exception) {
            trySend(Resource.Error(UnknownHostException()))
        }
        awaitClose { cancel() }
    }

    override fun getMovieImageDetail(movieId: Int): Flow<Resource<MovieImageResponse>> =
        callbackFlow {
            try {
                val response = tmdbDataSource.getMovieImageDetail(movieId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        trySend(Resource.Success(it))
                    } ?: kotlin.run {
                        trySend(Resource.Fail(null))
                    }
                } else {
                    trySend(Resource.Error(null))
                }
            } catch (ex: Exception) {
                trySend(Resource.Error(UnknownHostException()))
            }
            awaitClose { cancel() }
        }

}