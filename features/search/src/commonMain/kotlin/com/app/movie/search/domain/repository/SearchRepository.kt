package com.app.movie.search.domain.repository

import com.app.movie.core.data.model.HomeMovie

interface SearchRepository {
    suspend fun getSearchItems(query: String): List<HomeMovie>
}