package com.app.movie.core.di

import com.app.movie.core.data.local.AppDatabase
import com.app.movie.core.data.local.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun dataBaseModule(): Module = module {
    single<AppDatabase> { getDatabaseBuilder() }
}