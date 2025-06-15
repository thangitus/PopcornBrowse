package com.app.movie.di

import com.app.movie.core.di.coreModule
import com.app.movie.home.di.homeModule
import com.app.movie.detail.di.detailModule
import com.app.movie.network.di.networkModule

fun appModule() =
    listOf(
        coreModule,
        networkModule,
        homeModule,
        detailModule
    )
