package com.app.movie.core.domain.mapper

import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.data.model.movie.Movie
import com.app.movie.core.utils.getYear


fun List<Movie>.toHomeMovie(): List<HomeMovie> = map {
    HomeMovie(
        id = it.id,
        title = it.title.toString(),
        posterPath = it.posterPath.pathToUrl(),
        backdropPath = it.backdropPath.pathToUrl(),
        voteAverage = it.voteAverage,
        releaseYear = it.releaseDate?.getYear() ?: 0,
    )
}