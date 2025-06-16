package com.app.movie

import android.app.Application
import com.app.movie.di.initKoin
import com.app.movie.navigation.screenRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@App)
        }
        screenRegistry()
    }
}