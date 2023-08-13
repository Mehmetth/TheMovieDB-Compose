package com.mehmetpetek.themoviedbcompose.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.mehmetpetek.themoviedbcompose.presentation.screens.HomeScreenRoute

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    this.navigate(homeNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreen() {
    composable(route = homeNavigationRoute) {
        HomeScreenRoute()
    }
}