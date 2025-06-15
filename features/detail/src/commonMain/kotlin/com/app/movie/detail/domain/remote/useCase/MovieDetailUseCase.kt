package com.app.movie.detail.domain.remote.useCase

import com.app.movie.core.data.model.ApiResponse
import com.app.movie.detail.domain.remote.model.MovieDetail
import com.app.movie.detail.domain.remote.repository.MovieDetailRepository
import com.app.movie.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

interface MovieDetailUseCase {
    suspend operator fun invoke(id: Long): Flow<ApiResponse<MovieDetail>>
}

class MovieDetailUseCaseImpl(
    private val movieDetailRepository: MovieDetailRepository,
) : MovieDetailUseCase {
    override suspend fun invoke(id: Long): Flow<ApiResponse<MovieDetail>> = safeApiCall {
        val detailsResponse = movieDetailRepository.getDetails(id)
        return@safeApiCall detailsResponse
    }
}