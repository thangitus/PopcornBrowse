package com.app.movie.detail.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.app.movie.core.presentation.composable.FontSize
import androidx.compose.material3.Text
import com.app.movie.core.presentation.composable.Icon
import com.app.movie.core.presentation.theme.backgroundEnd
import com.app.movie.core.presentation.theme.primaryWhite
import com.app.movie.core.presentation.theme.secondaryGrey
import com.app.movie.core.presentation.theme.transparent
import com.app.movie.core.utils.getFormattedDate
import com.app.movie.detail.domain.remote.model.MovieDetail
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail,
) {
    val title = movieDetail.title
    val releaseDate = movieDetail.releaseDate.getFormattedDate()
    val runtime = "${movieDetail.runtime} min"
    val percent = movieDetail.voteAverage.toFloat()
    val backdropPath = movieDetail.backdropPath ?: ""

    Box(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        AsyncImage(
            model = backdropPath,
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(transparent, backgroundEnd),
                        200f,
                        850f
                    )
                )
        )
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),
        ) {
            RateProgressCircle(
                percent = percent,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    color = primaryWhite,
                    fontSize = FontSize.LARGE_X,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(
                        text = releaseDate,
                        color = secondaryGrey,
                        fontSize = FontSize.LARGE,
                    )
                    Text(
                        text = "•",
                        color = secondaryGrey,
                        fontSize = FontSize.LARGE,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            icon = FontAwesomeIcons.Regular.Clock,
                            modifier = Modifier.padding(end = 6.dp),
                            color = secondaryGrey,
                            size = 20,
                        )
                        Text(
                            text = runtime,
                            color = secondaryGrey,
                            fontSize = FontSize.LARGE,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailHeaderPreview() {
    MovieDetailHeader(mockMovieDetail)
}