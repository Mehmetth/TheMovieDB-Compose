package com.mehmetpetek.themoviedbcompose.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mehmetpetek.themoviedbcompose.presentation.components.CardItem
import com.mehmetpetek.themoviedbcompose.presentation.components.LoadingContent
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.GoToMovieDetail -> {}
                is HomeEffect.ShowError -> Unit
            }
        }
    }

    HomeScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun HomeScreen(
    viewState: HomeState,
    onViewEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    viewState.allMovies
    LoadingContent(isLoading = viewState.isLoading, modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                viewState.allMovies.forEach {
                    CardItem(
                        title = stringResource(id = it.key.movieType),
                        items = it.value?.results?.toImmutableList()
                    )
                }
            }
        }
    }
}