package com.app.movie.home.presentation.model

import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.data.model.ScreenState

enum class HomeMovieSource {
    TRENDING, SEARCH
}

data class HomeState(
    val state: ScreenState = ScreenState.Loading,
    val source: HomeMovieSource = HomeMovieSource.TRENDING,
    val searchQuery: String = "",
    val homeMovies: List<HomeMovie> = emptyList()
)