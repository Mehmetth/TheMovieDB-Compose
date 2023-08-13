package com.mehmetpetek.themoviedbcompose.data.remote

import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieDetailResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieImageResponse
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") pageNumber: Int,
        @Query("sort_by") sortBy: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImageDetail(
        @Path("movie_id") movieId: Int
    ): Response<MovieImageResponse>
}