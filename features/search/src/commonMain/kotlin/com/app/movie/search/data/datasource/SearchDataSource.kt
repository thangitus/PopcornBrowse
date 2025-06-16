package com.app.movie.search.data.datasource

import com.app.movie.core.data.model.movie.MovieResponse

interface SearchDataSource {
    suspend fun getSearchItems(query: String): MovieResponse
}