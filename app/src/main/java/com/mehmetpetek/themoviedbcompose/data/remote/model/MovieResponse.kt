package com.mehmetpetek.themoviedbcompose.data.remote.model

import androidx.compose.runtime.Stable

@Stable
data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
)

@Stable
data class Result(
    val backdrop_path: String,
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
)