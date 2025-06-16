package com.app.movie.home.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.app.movie.home.presentation.HomeScreen
import com.app.movie.navigation.SharedScreen

val homeNavModule = screenModule {
    register<SharedScreen.Home> { HomeScreen }
}