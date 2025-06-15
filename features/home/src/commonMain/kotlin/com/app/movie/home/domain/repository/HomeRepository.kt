package com.app.movie.home.domain.repository

import com.app.movie.core.data.model.HomeMovie

interface HomeRepository {
    suspend fun getTrending(): List<HomeMovie>
}