package com.app.movie.home.di

import com.app.movie.core.data.local.AppDatabase
import com.app.movie.core.data.local.dao.TrendingMovieDao
import com.app.movie.home.data.repository.HomeRepositoryImpl
import com.app.movie.home.data.source.MovieDataSource
import com.app.movie.home.data.source.MovieDataSourceImpl
import com.app.movie.home.domain.repository.HomeRepository
import com.app.movie.home.domain.useCase.HomeUseCase
import com.app.movie.home.domain.useCase.HomeUseCaseImpl
import com.app.movie.home.presentation.HomeScreenModel
import com.app.movie.search.di.searchModule
import org.koin.dsl.module

val homeModule = module {
    single<TrendingMovieDao> {
        get<AppDatabase>().trendingDao()
    }
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<HomeRepository> {
        HomeRepositoryImpl(
            movieDataSource = get(),
            trendingMovieDao = get()
        )
    }
    single<HomeUseCase> { HomeUseCaseImpl(get()) }
    factory {
        HomeScreenModel(
            getHomeUseCase = get(),
            getSearchUseCase = get()
        )
    }

    includes(searchModule)
}

