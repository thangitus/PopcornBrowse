package com.app.movie.network.di

import com.app.movie.network.client.httpClient
import org.koin.dsl.module

val networkModule = module {
    single { httpClient }
}