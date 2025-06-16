package com.app.movie.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object Home : SharedScreen()
    data class Movie(val movieId: Long) : SharedScreen()

}