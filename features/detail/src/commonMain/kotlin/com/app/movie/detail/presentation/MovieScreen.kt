package com.app.movie.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.movie.core.data.model.ScreenState
import com.app.movie.core.presentation.composable.ErrorView
import com.app.movie.core.presentation.composable.Icon
import com.app.movie.core.presentation.composable.LoadingView
import com.app.movie.core.presentation.theme.backgroundEnd
import com.app.movie.core.utils.windowInsetsPadding
import com.app.movie.detail.presentation.composables.MovieDetailContent
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

data class MovieScreen(val id: Long) : Screen {
    override val key = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MovieScreenModel>()
        val uiState by screenModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = screenModel) {
            screenModel::handleAction.invoke(OnInitMovieScreen(id))
        }

        Box(Modifier.background(backgroundEnd).fillMaxSize()) {
            when (uiState.screenState) {
                is ScreenState.Loading -> LoadingView()
                is ScreenState.Error -> ErrorView(
                    errorMsg = (uiState.screenState as ScreenState.Error).errorMsg,
                    onRetry = {
                        screenModel::handleAction.invoke(OnInitMovieScreen(id))
                    }
                )

                is ScreenState.Default -> MovieDetailContent(
                    movieDetail = uiState.movieDetail,
                )
            }
            IconButton(
                onClick = { navigator.pop() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp).windowInsetsPadding(
                        WindowInsets.safeDrawing
                            .only(WindowInsetsSides.Top)
                            .asPaddingValues()
                            .calculateTopPadding()

                    )
            ) {
                Icon(
                    icon = FontAwesomeIcons.Solid.ArrowLeft,
                    color = Color.White,
                    size = 24,
                )
            }
        }
    }
}