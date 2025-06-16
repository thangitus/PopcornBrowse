import com.app.movie.core.data.local.dao.MovieDetailDao
import com.app.movie.core.data.local.entity.MovieDetailEntity
import com.app.movie.core.data.model.movie_detail.MovieDetailResponse
import com.app.movie.core.data.model.movie_detail.MovieGenre
import com.app.movie.detail.data.remote.datasource.DetailMovieDatasource
import com.app.movie.detail.data.remote.repository.MovieDetailDetailRepositoryImpl
import com.app.movie.detail.domain.remote.mapper.toUiModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

private val dummyResponse = MovieDetailResponse(
    adult = false,
    backdropPath = "/back.jpg",
    budget = 1000L,
    movieGenres = listOf(MovieGenre(id = 1, name = "Thriller")),
    homepage = "https://example.com",
    id = 42L,
    imdbId = "tt000",
    originalLanguage = "en",
    originalTitle = "Orig",
    overview = "Overview text",
    popularity = 123.0,
    posterPath = "/post.jpg",
    productionCompanies = emptyList(),
    productionCountries = emptyList(),
    releaseDate = "2025-01-01",
    revenue = 2000L,
    runtime = 120,
    spokenLanguages = emptyList(),
    status = "Released",
    tagline = "Tagline",
    title = "Title",
    video = false,
    voteAverage = 7.5,
    voteCount = 100
)

class MovieDetailRepositoryImplTest {

    @Test
    fun `cache hit returns local without remote`() = runTest {
        val remoteMock = mock<DetailMovieDatasource>()
        val daoMock = mock<MovieDetailDao>()
        val repo = MovieDetailDetailRepositoryImpl(remoteMock, daoMock)

        val cachedEntity = MovieDetailEntity(detail = dummyResponse)
        everySuspend { daoMock.getMovieDetail(42L) } returns cachedEntity

        val result = repo.getDetails(42L)

        verifySuspend(VerifyMode.exactly(0)) { remoteMock.getDetails(any()) }
        verifySuspend(VerifyMode.exactly(0)) { daoMock.insert(any()) }
        assertSame(cachedEntity.detail.toUiModel().overview, result.overview)
    }

    @Test
    fun `cache miss fetches remote and inserts then returns`() = runTest {
        val remoteMock = mock<DetailMovieDatasource>()
        val daoMock = mock<MovieDetailDao>()
        val repo = MovieDetailDetailRepositoryImpl(remoteMock, daoMock)

        everySuspend { daoMock.getMovieDetail(99L) } returns null
        everySuspend { remoteMock.getDetails(99L) } returns dummyResponse
        everySuspend { daoMock.insert(any()) } returns Unit

        val result = repo.getDetails(99L)

        verifySuspend(VerifyMode.exactly(1)) { remoteMock.getDetails(99L) }
        verifySuspend(VerifyMode.exactly(1)) { daoMock.insert(any()) }
        assertEquals(dummyResponse.toUiModel().overview, result.overview)
    }
}