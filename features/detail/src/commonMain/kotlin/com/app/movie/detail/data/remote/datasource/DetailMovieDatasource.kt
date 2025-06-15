package com.app.movie.detail.data.remote.datasource

import com.app.movie.core.data.model.movie_detail.MovieDetailResponse

interface DetailMovieDatasource {
    suspend fun getDetails(id: Long): MovieDetailResponse
}