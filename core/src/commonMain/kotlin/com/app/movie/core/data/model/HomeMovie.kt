package com.app.movie.core.data.model

data class HomeMovie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val releaseYear: Int
)