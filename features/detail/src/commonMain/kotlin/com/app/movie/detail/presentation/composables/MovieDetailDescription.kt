package com.app.movie.detail.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.app.movie.core.presentation.composable.TextStyles
import com.app.movie.core.presentation.theme.darkenGrey
import com.app.movie.core.presentation.theme.primaryWhite
import com.app.movie.detail.domain.remote.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailTextContent(
    movieDetail: MovieDetail,
    modifier: Modifier = Modifier,
) {
    val contents = listOf(
        "Overview" to movieDetail.overview,
        "Tagline" to movieDetail.tagline,
        "Release Date" to movieDetail.releaseDate,
        "Runtime" to "${movieDetail.runtime} min",
        "Budget" to "${movieDetail.budget} $",
        "Revenue" to "${movieDetail.revenue} $",
        "Vote Average" to "${movieDetail.voteAverage} / 10",
        "Vote Count" to "${movieDetail.voteCount}",
        "Homepage" to movieDetail.homepage,
        "Status" to movieDetail.status,
    )
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val uriHandler = LocalUriHandler.current

        contents.forEach { (title, content) ->
            val isLink = content.startsWith("http")
            ContentItem(
                title = title,
                content = content,
                isLink = isLink,
                onLinkClick = { uriHandler.openUri(content) }
            )
        }

        Text(
            text = "Genres",
            color = primaryWhite,
            style = TextStyles.Heading4,
        )
        LazyRow(contentPadding = PaddingValues(start = 16.dp, bottom = 30.dp)) {
            items(movieDetail.movieGenres) {
                Row(
                    modifier = Modifier.padding(end = 14.dp)
                ) {
                    Text(
                        text = it.name,
                        color = primaryWhite,
                        modifier = Modifier
                            .background(darkenGrey, shape = RoundedCornerShape(34.dp))
                            .padding(horizontal = 18.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }

}

@Composable
private fun ContentItem(
    title: String,
    content: String,
    isLink: Boolean = false,
    onLinkClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = primaryWhite,
            style = TextStyles.Heading4,
        )

        if (isLink) {
            Text(
                text = content,
                color = primaryWhite.copy(alpha = 0.8F),
                fontWeight = FontWeight.Light,
                style = TextStyles.Body1Regular,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable(onClick = onLinkClick)
            )
        } else {
            Text(
                text = content,
                color = primaryWhite.copy(alpha = 0.8F),
                fontWeight = FontWeight.Light,
                style = TextStyles.Body1Regular,
            )
        }
    }
}


@Preview
@Composable
fun MovieDetailDescriptionPreview() {
    MovieDetailTextContent(
        mockMovieDetail
    )
}