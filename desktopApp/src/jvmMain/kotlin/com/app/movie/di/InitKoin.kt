package com.app.movie.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(appModule())
    }
}