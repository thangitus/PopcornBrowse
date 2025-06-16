package com.app.movie.detail.domain.remote.mapper

import com.app.movie.core.data.model.movie_detail.MovieDetailResponse
import com.app.movie.core.domain.mapper.pathToUrl
import com.app.movie.detail.domain.remote.model.MovieDetail

fun MovieDetailResponse.toUiModel(): MovieDetail {
    return MovieDetail(
        id = id,
        adult = adult,
        backdropPath = backdropPath.pathToUrl(),
        budget = budget,
        movieGenres = movieGenres,
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath.pathToUrl(),
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}