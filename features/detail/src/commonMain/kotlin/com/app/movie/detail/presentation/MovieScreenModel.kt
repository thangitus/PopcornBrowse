package com.app.movie.detail.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.app.movie.core.data.model.ApiResponse
import com.app.movie.core.data.model.ScreenState
import com.app.movie.detail.domain.remote.model.MovieDetail
import com.app.movie.detail.domain.remote.useCase.MovieDetailUseCase
import com.app.movie.detail.presentation.model.MovieDetailState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieScreenModel(
    private val movieDetailUseCase: MovieDetailUseCase,
) : StateScreenModel<MovieDetailState>(MovieDetailState()) {

    private fun setSuccessState(movieDetail: MovieDetail) = mutableState.update {
        it.copy(
            screenState = ScreenState.Default,
            movieDetail = movieDetail
        )
    }

    private fun setLoadingState() = mutableState.update {
        it.copy(
            screenState = ScreenState.Loading
        )
    }

    private fun setErrorState(message: String) = mutableState.update {
        it.copy(screenState = ScreenState.Error(message))
    }

    private fun getMovie(id: Long) = screenModelScope.launch {
        movieDetailUseCase(id).collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setSuccessState(result.data)
                is ApiResponse.Error -> setErrorState(result.message)
                is ApiResponse.Loading -> setLoadingState()
            }
        }
    }

    internal fun handleAction(action: MovieScreenActions) = when (action) {
        is OnInitMovieScreen -> getMovie(action.id)
    }
}
