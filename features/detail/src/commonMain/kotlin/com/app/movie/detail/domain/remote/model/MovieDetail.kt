package com.app.movie.detail.domain.remote.model

import com.app.movie.core.data.model.movie_detail.MovieGenre
import com.app.movie.core.data.model.movie_detail.ProductionCompany
import com.app.movie.core.data.model.movie_detail.ProductionCountry
import com.app.movie.core.data.model.movie_detail.SpokenLanguage

data class MovieDetail(
    val id: Long = 0L,
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val budget: Long = 0L,
    val movieGenres: List<MovieGenre> = emptyList(),
    val homepage: String = "",
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val releaseDate: String = "",
    val revenue: Long = 0L,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)
