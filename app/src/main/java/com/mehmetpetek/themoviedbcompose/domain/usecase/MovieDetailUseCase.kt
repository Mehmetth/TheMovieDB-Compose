package com.mehmetpetek.themoviedbcompose.domain.usecase

import com.mehmetpetek.themoviedbcompose.data.remote.model.Resource
import com.mehmetpetek.themoviedbcompose.domain.repository.TMDBRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


class MovieDetailUseCase @Inject constructor(
    private val tmdbRepository: TMDBRepository
) {
    operator fun invoke(movieId: Int): Flow<MovieDetailState> = callbackFlow {

        val hashMap: HashMap<MovieDetailType, Any> = hashMapOf()

        val getMovieDetail = tmdbRepository.getMovieDetail(movieId)
        val getMovieImageDetail = tmdbRepository.getMovieImageDetail(movieId)

        getMovieDetail.combine(getMovieImageDetail) { movieDetail, movieImageDetail ->
            if (movieDetail is Resource.Success && movieImageDetail is Resource.Success) {
                hashMap[MovieDetailType.DETAIL] = movieDetail.data as Any
                hashMap[MovieDetailType.IMAGE_DETAIL] = movieImageDetail.data as Any
                MovieDetailState.Success(hashMap)
            } else {
                MovieDetailState.Error(movieDetail.message)
            }
        }.collect {
            trySend(it)
        }
        awaitClose { cancel() }
    }

    sealed interface MovieDetailState {
        data class Success(val movieDetailResponse: HashMap<MovieDetailType, Any>) :
            MovieDetailState

        data class Error(val throwable: Throwable?) : MovieDetailState
    }

    enum class MovieDetailType {
        DETAIL,
        IMAGE_DETAIL
    }
}