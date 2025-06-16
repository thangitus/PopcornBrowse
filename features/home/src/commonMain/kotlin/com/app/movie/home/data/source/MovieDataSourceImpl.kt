package com.app.movie.home.data.source

import com.app.movie.core.data.model.movie.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MovieDataSourceImpl(
    private val httpClient: HttpClient
) : MovieDataSource {

    override suspend fun getTrending(): MovieResponse {
        val response = httpClient.get("trending/movie/day")
        return response.body()
    }

}