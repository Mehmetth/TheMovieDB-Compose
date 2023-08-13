package com.mehmetpetek.themoviedbcompose.data.local.model

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Stable
@Entity(tableName = "movie")
data class TMDBEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val moviePosterUrl: String?,
    val movieBackdropUrl: String?,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
    val sortBy: String,
    val totalPage: Int,
    var page: Int = 1
)