package com.app.movie.detail.domain.remote.repository

import com.app.movie.detail.domain.remote.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getDetails(id: Long): MovieDetail
}