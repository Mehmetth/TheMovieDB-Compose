package com.mehmetpetek.themoviedbcompose.domain.repository

import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieDetailResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieImageResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.Resource
import kotlinx.coroutines.flow.Flow

interface TMDBRepository {
    fun getDiscoverMovies(page: Int, sortBy: String): Flow<Resource<MovieResponse>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetailResponse>>
    fun getMovieImageDetail(movieId: Int): Flow<Resource<MovieImageResponse>>
}