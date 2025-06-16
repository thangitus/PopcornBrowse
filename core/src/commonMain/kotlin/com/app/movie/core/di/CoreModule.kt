package com.app.movie.core.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect fun dataBaseModule(): Module

val coreModule = module {
    includes(dataBaseModule())
}