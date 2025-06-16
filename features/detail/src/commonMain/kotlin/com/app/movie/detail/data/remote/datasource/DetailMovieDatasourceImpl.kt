package com.app.movie.detail.data.remote.datasource

import com.app.movie.core.data.model.movie_detail.MovieDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class DetailMovieDatasourceImpl(
    private val httpClient: HttpClient
) : DetailMovieDatasource {
    override suspend fun getDetails(id: Long): MovieDetailResponse {
        val response = httpClient.get("movie/$id")
        return response.body()
    }

}