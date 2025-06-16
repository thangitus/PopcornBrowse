package com.app.movie.search.data.repository

import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.domain.mapper.toHomeMovie
import com.app.movie.search.data.datasource.SearchDataSource
import com.app.movie.search.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getSearchItems(query: String): List<HomeMovie> {
        val response = dataSource.getSearchItems(query)
        return response.results.toHomeMovie()
    }
}