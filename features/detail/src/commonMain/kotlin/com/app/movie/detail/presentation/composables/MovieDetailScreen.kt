package com.app.movie.detail.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.movie.core.data.model.movie_detail.MovieGenre
import com.app.movie.core.data.model.movie_detail.ProductionCompany
import com.app.movie.core.data.model.movie_detail.ProductionCountry
import com.app.movie.core.data.model.movie_detail.SpokenLanguage
import com.app.movie.core.presentation.theme.primaryWhite
import com.app.movie.detail.domain.remote.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        MovieDetailHeader(
            movieDetail = movieDetail,
        )
        HorizontalDivider(
            Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            1.dp,
            primaryWhite.copy(alpha = 0.2F)
        )
        MovieDetailTextContent(movieDetail)

    }
}

@Preview
@Composable
fun MovieScreenPreview() {
    MovieDetailContent(movieDetail = mockMovieDetail)
}


internal val mockMovieDetail = MovieDetail(
    id = 1097311L,
    adult = false,
    backdropPath = "/aQ5nvQGT6mM6TliOM5iSgrKVF4C.jpg",
    budget = 0L,
    movieGenres = listOf(
        MovieGenre(id = 53, name = "Thriller"),
        MovieGenre(id = 18, name = "Drama")
    ),
    homepage = "https://tv.apple.com/movie/umc.cmc.1al6pi2y17htw3z98tbacgo55",
    imdbId = "tt27052633",
    originalLanguage = "en",
    originalTitle = "Echo Valley",
    overview = "Kate lives a secluded life—until her troubled daughter shows up, frightened and covered in someone else's blood. As Kate unravels the shocking truth, she learns just how far a mother will go to try to save her child.",
    popularity = 105.1347,
    posterPath = "/4akm5FQdZFfNpYGgVyOEXi72eby.jpg",
    productionCompanies = listOf(
        ProductionCompany(
            id = 79315,
            logoPath = "/8rAmlU9kIQKBenqQVlz6hUyV1Z4.png",
            name = "Black Bicycle Entertainment",
            originCountry = "US"
        ),
        ProductionCompany(
            id = 194232,
            logoPath = "/oE7H93u8sy5vvW5EH3fpCp68vvB.png",
            name = "Apple Studios",
            originCountry = "US"
        ),
        ProductionCompany(
            id = 221347,
            logoPath = "/6Ry6uNBaa0IbbSs1XYIgX5DkA9r.png",
            name = "Scott Free Productions",
            originCountry = "US"
        )
    ),
    productionCountries = listOf(
        ProductionCountry(
            iso31661 = "US",
            name = "United States of America"
        )
    ),
    releaseDate = "2025-06-13",
    revenue = 0L,
    runtime = 105,
    spokenLanguages = listOf(
        SpokenLanguage(
            englishName = "English",
            iso6391 = "en",
            name = "English"
        )
    ),
    status = "Released",
    tagline = "A mother and daughter are bound by a dark secret.",
    title = "Echo Valley",
    video = false,
    voteAverage = 6.6,
    voteCount = 50
)
