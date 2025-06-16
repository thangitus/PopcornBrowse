package com.app.movie.search.di

import com.app.movie.search.data.datasource.SearchDataSource
import com.app.movie.search.data.datasource.SearchDataSourceImpl
import com.app.movie.search.data.repository.SearchRepositoryImpl
import com.app.movie.search.domain.repository.SearchRepository
import com.app.movie.search.domain.useCase.GetSearchUseCase
import com.app.movie.search.domain.useCase.GetSearchUseCaseImpl
import org.koin.dsl.module

val searchModule = module {
    single<SearchDataSource> { SearchDataSourceImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<GetSearchUseCase> { GetSearchUseCaseImpl(get()) }
}