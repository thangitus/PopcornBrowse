package com.app.movie.home.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.app.movie.core.data.model.ApiResponse
import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.data.model.ScreenState
import com.app.movie.home.domain.useCase.HomeUseCase
import com.app.movie.home.presentation.model.HomeMovieSource
import com.app.movie.home.presentation.model.HomeState
import com.app.movie.search.domain.useCase.GetSearchUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("OPT_IN_USAGE", "UNCHECKED_CAST")
class HomeScreenModel(
    private val getHomeUseCase: HomeUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : StateScreenModel<HomeState>(HomeState()) {
    init {
        state.map { it.searchQuery }
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isBlank()) getHomeUseCase()
                else getSearchUseCase(query)
            }.onStart {
                setLoading()
            }.onEach { result ->
                when (result) {
                    is ApiResponse.Loading -> setLoading()
                    is ApiResponse.Success -> {
                        when (result.data) {
                            is HomeState -> setMovies(
                                movies = (result.data as HomeState).homeMovies,
                                source = HomeMovieSource.TRENDING
                            )

                            is List<*> -> setMovies(
                                movies = result.data as List<HomeMovie>,
                                source = HomeMovieSource.SEARCH
                            )

                            else -> throw IllegalArgumentException("Unexpected data type: ${result.data::class}")
                        }
                    }

                    is ApiResponse.Error -> setError(result.message)
                }
            }.launchIn(screenModelScope)
    }

    fun getTrendingMovies() = screenModelScope.launch {
        getHomeUseCase().collectLatest { result ->
            when (result) {
                is ApiResponse.Success -> setMovies(
                    movies = result.data.homeMovies,
                    source = HomeMovieSource.TRENDING
                )

                is ApiResponse.Error -> setError(result.message)
                is ApiResponse.Loading -> setLoading()
            }
        }
    }

    private fun setLoading() = mutableState.update {
        it.copy(state = ScreenState.Loading)
    }

    private fun setError(msg: String) = mutableState.update {
        it.copy(state = ScreenState.Error(msg))
    }

    private fun setMovies(movies: List<HomeMovie>, source: HomeMovieSource) = mutableState.update {
        it.copy(
            state = ScreenState.Default,
            homeMovies = movies,
            source = source
        )
    }

    fun searchMovies(query: String) = mutableState.update {
        it.copy(
            searchQuery = query,
        )
    }

}