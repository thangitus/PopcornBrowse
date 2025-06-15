package com.app.movie.navigation

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.app.movie.detail.navigation.detailNavModule
import com.app.movie.home.navigation.homeNavModule

fun screenRegistry() = ScreenRegistry {
    homeNavModule()
    detailNavModule()
}