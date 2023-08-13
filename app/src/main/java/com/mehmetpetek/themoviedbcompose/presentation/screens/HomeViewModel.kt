package com.mehmetpetek.themoviedbcompose.presentation.screens

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.mehmetpetek.themoviedbcompose.R
import com.mehmetpetek.themoviedbcompose.data.remote.model.MovieResponse
import com.mehmetpetek.themoviedbcompose.domain.usecase.AllMoviesUseCase
import com.mehmetpetek.themoviedbcompose.presentation.base.BaseViewModel
import com.mehmetpetek.themoviedbcompose.presentation.base.IEffect
import com.mehmetpetek.themoviedbcompose.presentation.base.IEvent
import com.mehmetpetek.themoviedbcompose.presentation.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allMoviesUseCase: AllMoviesUseCase,
    private val application: Application
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>() {

    init {
        allMovies(
            getCurrentState().popularityMoviesPage,
            getCurrentState().revenueMoviesPage,
            getCurrentState().primaryReleaseDateMoviesPage,
            getCurrentState().voteAverageMoviesPage
        )
    }

    override fun setInitialState() = HomeState()

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadMore -> {
                when (event.title) {
                    application.applicationContext.getString(R.string.popularity_movies) -> {
                        setState(getCurrentState().copy(popularityMoviesPage = getCurrentState().popularityMoviesPage + 1))
                    }

                    application.applicationContext.getString(R.string.revenue_movies) -> {
                        setState(getCurrentState().copy(revenueMoviesPage = getCurrentState().revenueMoviesPage + 1))
                    }

                    application.applicationContext.getString(R.string.primary_release_date_movies) -> {
                        setState(getCurrentState().copy(primaryReleaseDateMoviesPage = getCurrentState().primaryReleaseDateMoviesPage + 1))
                    }

                    application.applicationContext.getString(R.string.vote_average_movies) -> {
                        setState(getCurrentState().copy(voteAverageMoviesPage = getCurrentState().voteAverageMoviesPage + 1))
                    }
                }
                allMovies(
                    getCurrentState().popularityMoviesPage,
                    getCurrentState().revenueMoviesPage,
                    getCurrentState().primaryReleaseDateMoviesPage,
                    getCurrentState().voteAverageMoviesPage
                )
            }

            is HomeEvent.OnClickMovieDetail -> {
                setEffect { HomeEffect.GoToMovieDetail(event.movieId) }
            }
        }
    }


    private fun allMovies(
        popularityMoviesPage: Int,
        revenueMoviesPage: Int,
        primaryReleaseDateMoviesPage: Int,
        voteAverageMoviesPage: Int
    ) {
        setState(getCurrentState().copy(isLoading = true))
        viewModelScope.launch {
            allMoviesUseCase.invoke(
                popularityMoviesPage,
                revenueMoviesPage,
                primaryReleaseDateMoviesPage,
                voteAverageMoviesPage
            ).collect {
                when (it) {
                    is AllMoviesUseCase.AllMoviesState.Success -> {
                        setState(
                            getCurrentState().copy(
                                isLoading = false,
                                allMovies = it.allMovies
                            )
                        )
                    }

                    is AllMoviesUseCase.AllMoviesState.NotData -> {
                        setState(getCurrentState().copy(isLoading = false, allMovies = hashMapOf()))
                    }

                    is AllMoviesUseCase.AllMoviesState.Error -> {
                        setState(getCurrentState().copy(isLoading = false, allMovies = hashMapOf()))
                        setEffect { HomeEffect.ShowError(it.throwable) }
                    }
                }
            }
        }
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val popularityMoviesPage: Int = 1,
    val revenueMoviesPage: Int = 1,
    val primaryReleaseDateMoviesPage: Int = 1,
    val voteAverageMoviesPage: Int = 1,
    val allMovies: HashMap<AllMoviesUseCase.MovieType, MovieResponse?> = hashMapOf(),
) : IState

sealed interface HomeEffect : IEffect {
    data class ShowError(val throwable: Throwable?) : HomeEffect
    data class GoToMovieDetail(val movieId: Int) : HomeEffect
}

sealed interface HomeEvent : IEvent {
    data class LoadMore(val title: String) : HomeEvent
    data class OnClickMovieDetail(val movieId: Int) : HomeEvent
}