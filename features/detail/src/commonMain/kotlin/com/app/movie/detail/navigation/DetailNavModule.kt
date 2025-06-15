package com.app.movie.detail.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.app.movie.detail.presentation.MovieScreen
import com.app.movie.navigation.SharedScreen

val detailNavModule = screenModule {
    register<SharedScreen.Movie> { MovieScreen(it.movieId) }
}