package com.mehmetpetek.themoviedbcompose.data.mapper

import com.mehmetpetek.themoviedbcompose.data.local.model.TMDBEntity
import com.mehmetpetek.themoviedbcompose.data.remote.model.Result

fun List<TMDBEntity>.movieToEntities(): List<Result> {
    return map {
        Result(
            backdrop_path = it.movieBackdropUrl.orEmpty(),
            id = it.id,
            poster_path = it.moviePosterUrl.orEmpty(),
            release_date = it.releaseDate,
            title = it.title,
            vote_average = it.rating
        )
    }
}

fun List<Result>.entitiesToMovie(page: Int, sortBy: String, totalPage: Int): List<TMDBEntity> {
    return map {
        TMDBEntity(
            id = it.id,
            title = it.title,
            moviePosterUrl = it.poster_path,
            movieBackdropUrl = it.backdrop_path,
            overview = "",
            rating = it.vote_average,
            releaseDate = it.release_date,
            sortBy = sortBy,
            totalPage = totalPage,
            page = page
        )
    }
}