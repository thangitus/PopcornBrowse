package com.app.movie.home.data.source

import com.app.movie.core.data.model.movie.MovieResponse


interface MovieDataSource {
    suspend fun getTrending(): MovieResponse
}