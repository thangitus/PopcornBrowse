package com.app.movie.network.utils

import com.app.movie.core.data.model.ApiResponse
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ApiResponse<T>> = flow {
    emit(ApiResponse.Loading)
    runCatching {
        val response = apiCall()
        emit(ApiResponse.Success(response))
    }.onFailure {
        Napier.e("Error on get api data", it)
        emit(ApiResponse.Error(it.message ?: "Unknown error occurred"))
    }
}