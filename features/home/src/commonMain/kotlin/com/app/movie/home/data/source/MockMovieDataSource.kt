package com.app.movie.home.data.source

import com.app.movie.core.data.model.movie.MovieResponse

class MockMovieDataSource : MovieDataSource {
    override suspend fun getTrending(): MovieResponse {
        TODO("Not yet implemented")
    }
}