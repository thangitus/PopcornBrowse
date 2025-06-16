package com.app.movie.core.data.model

sealed class ScreenState {
    data object Default : ScreenState()
    data object Loading : ScreenState()
    data class Error(val errorMsg: String = "") : ScreenState()
}