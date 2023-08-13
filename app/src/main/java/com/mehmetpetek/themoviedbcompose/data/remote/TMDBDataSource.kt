package com.mehmetpetek.themoviedbcompose.data.remote

import javax.inject.Inject

class TMDBDataSource @Inject constructor(private val tmdbService: TMDBService) {

    suspend fun getDiscoverMovies(page: Int, sortBy: String) =
        tmdbService.getDiscoverMovies(page, sortBy)

    suspend fun getMovieDetail(movieId: Int) = tmdbService.getMovieDetail(movieId)
    suspend fun getMovieImageDetail(movieId: Int) = tmdbService.getMovieImageDetail(movieId)
}