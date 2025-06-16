package com.app.movie.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.presentation.composable.FontSize
import com.app.movie.core.presentation.composable.RatingLevel
import androidx.compose.material3.Text
import com.app.movie.core.presentation.theme.primaryWhite

@Composable
fun HomeMovieItem(
    movie: HomeMovie,
    onGoToMovie: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .wrapContentSize()
            .padding(end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onGoToMovie(movie.id)
            }
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        0F to Color.Transparent,
                        .5F to Color.Black.copy(alpha = 0.5F),
                        1F to Color.Black.copy(alpha = 0.8F)
                    )
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 10.dp, vertical = 13.dp)
        ) {
            Column {
                Text(
                    text = movie.title,
                    color = primaryWhite,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingLevel(movie.voteAverage, 10)
                    Text(
                        text = movie.releaseYear.toString(),
                        color = primaryWhite,
                        fontSize = FontSize.SMALL,
                    )
                }
            }
        }
    }
}

@Composable
fun HomeMoviesList(
    movies: List<HomeMovie>,
    onGoToMovie: (Long) -> Unit,
    modifier: Modifier = Modifier,
    gridState: LazyGridState = rememberLazyGridState()
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = spacedBy(12.dp),
        horizontalArrangement = spacedBy(12.dp),
        state = gridState
    ) {
        items(movies, key = { it.id }) { movie ->
            HomeMovieItem(
                movie = movie,
                onGoToMovie = onGoToMovie,
            )
        }
    }
}
