package com.mehmetpetek.themoviedbcompose.data.remote.model

import androidx.compose.runtime.Stable

@Stable
data class MovieImageResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)

@Stable
data class Backdrop(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)

@Stable
data class Logo(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)

@Stable
data class Poster(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)