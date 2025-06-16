package com.app.movie.core.di

import com.app.movie.core.data.local.AppDatabase
import com.app.movie.core.data.local.getDatabaseBuilder
import org.koin.dsl.module

actual fun dataBaseModule() = module {
    single<AppDatabase> { getDatabaseBuilder(get()) }
}