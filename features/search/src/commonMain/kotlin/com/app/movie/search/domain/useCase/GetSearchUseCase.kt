package com.app.movie.search.domain.useCase

import com.app.movie.core.data.model.ApiResponse
import com.app.movie.core.data.model.HomeMovie
import com.app.movie.network.utils.safeApiCall
import com.app.movie.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

interface GetSearchUseCase {
    operator fun invoke(query: String): Flow<ApiResponse<List<HomeMovie>>>
}

class GetSearchUseCaseImpl(
    private val repository: SearchRepository
) : GetSearchUseCase {
    override fun invoke(query: String): Flow<ApiResponse<List<HomeMovie>>> = safeApiCall {
        repository.getSearchItems(query)
    }
}