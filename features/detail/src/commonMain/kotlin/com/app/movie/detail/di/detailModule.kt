package com.app.movie.detail.di

import com.app.movie.core.data.local.AppDatabase
import com.app.movie.core.data.local.dao.MovieDetailDao
import com.app.movie.detail.data.remote.datasource.DetailMovieDatasource
import com.app.movie.detail.data.remote.datasource.DetailMovieDatasourceImpl
import com.app.movie.detail.data.remote.repository.MovieDetailDetailRepositoryImpl
import com.app.movie.detail.domain.remote.repository.MovieDetailRepository
import com.app.movie.detail.domain.remote.useCase.MovieDetailUseCase
import com.app.movie.detail.domain.remote.useCase.MovieDetailUseCaseImpl
import com.app.movie.detail.presentation.MovieScreenModel
import org.koin.dsl.module

val detailModule = module {
    single<MovieDetailDao> {
        get<AppDatabase>().movieDetailDao()
    }
    single<DetailMovieDatasource> { DetailMovieDatasourceImpl(get()) }
    single<MovieDetailRepository> { MovieDetailDetailRepositoryImpl(remote = get(), local = get()) }
    single<MovieDetailUseCase> {
        MovieDetailUseCaseImpl(
            movieDetailRepository = get(),
        )
    }
    factory {
        MovieScreenModel(
            movieDetailUseCase = get(),
        )
    }
}