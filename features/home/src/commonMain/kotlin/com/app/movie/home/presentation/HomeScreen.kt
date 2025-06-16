package com.app.movie.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.movie.core.data.model.ScreenState
import com.app.movie.core.presentation.composable.ErrorView
import com.app.movie.core.presentation.composable.LoadingView
import com.app.movie.home.presentation.composables.HomeMoviesContent
import com.app.movie.navigation.SharedScreen

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val uiState by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow


        fun onGoToMovie(movieId: Long) =
            navigator.push(ScreenRegistry.get(SharedScreen.Movie(movieId)))

        val query = uiState.searchQuery
        when (uiState.state) {
            is ScreenState.Default -> HomeMoviesContent(
                movies = uiState.homeMovies,
                homeMovieSource = uiState.source,
                searchQuery = query,
                onGoToMovie = { onGoToMovie(it) },
                onQueryChange = {
                    screenModel.searchMovies(it)
                }
            )

            is ScreenState.Error -> ErrorView(
                errorMsg = (uiState.state as ScreenState.Error).errorMsg,
                onRetry = {
                    screenModel.getTrendingMovies()
                }
            )

            is ScreenState.Loading -> LoadingView()
        }
    }
}