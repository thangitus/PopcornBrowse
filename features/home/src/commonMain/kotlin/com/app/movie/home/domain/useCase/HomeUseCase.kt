package com.app.movie.home.domain.useCase


import com.app.movie.core.data.model.ApiResponse
import com.app.movie.home.domain.repository.HomeRepository
import com.app.movie.home.presentation.model.HomeState
import com.app.movie.network.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    suspend operator fun invoke(): Flow<ApiResponse<HomeState>>
}

class HomeUseCaseImpl(
    private val repository: HomeRepository
) : HomeUseCase {

    private fun onGetMovies(): Flow<ApiResponse<HomeState>> = safeApiCall {
        val trendingResponse = repository.getTrending()
        HomeState(homeMovies = trendingResponse)
    }


    override suspend fun invoke(): Flow<ApiResponse<HomeState>> = onGetMovies()

}