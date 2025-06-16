package com.app.movie.detail.presentation.model

import com.app.movie.core.data.model.ScreenState
import com.app.movie.detail.domain.remote.model.MovieDetail

data class MovieDetailState(
    val screenState: ScreenState = ScreenState.Loading,
    val movieDetail: MovieDetail = MovieDetail(),
)