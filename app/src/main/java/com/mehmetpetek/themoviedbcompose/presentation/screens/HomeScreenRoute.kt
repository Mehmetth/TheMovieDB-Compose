package com.mehmetpetek.themoviedbcompose.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mehmetpetek.themoviedbcompose.presentation.components.LoadingContent


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


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen(
    viewState: HomeState,
    onViewEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LoadingContent(isLoading = viewState.isLoading, modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue))
        /*
        LazyVerticalStaggeredGrid(
            modifier = Modifier,
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
        }
        */
    }
}