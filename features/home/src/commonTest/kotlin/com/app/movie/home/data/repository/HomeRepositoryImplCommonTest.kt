package com.app.movie.home.data.repository


import com.app.movie.core.data.local.dao.TrendingMovieDao
import com.app.movie.core.data.local.entity.TrendingMovieEntity
import com.app.movie.core.data.model.movie.Movie
import com.app.movie.core.data.model.movie.MovieResponse
import com.app.movie.core.domain.mapper.toHomeMovie
import com.app.movie.home.data.source.MovieDataSource
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryImplCommonTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    private val fakeMovie = Movie(
        adult = false,
        backdropPath = "/uIpJPDNFoeX0TVml9smPrs9KUVx.jpg",
        genreIds = listOf(27, 9648),
        id = 574475L,
        originalLanguage = "en",
        originalTitle = "Final Destination Bloodlines",
        overview = "Plagued by a violent recurring nightmare, college student Stefanie heads home to track down the one person who might be able to break the cycle and save her family from the grisly demise that inevitably awaits them all.",
        popularity = 1059.8849,
        posterPath = "/6WxhEvFsauuACfv8HyoVX6mZKFj.jpg",
        releaseDate = "2025-05-14",
        title = "Final Destination Bloodlines",
        video = false,
        voteAverage = 7.031,
        voteCount = 702
    )

    private val fakeResponse = MovieResponse(
        results = listOf(fakeMovie),
        page = 1, totalPages = 1, totalResults = 1
    )

    @Test
    fun `no cache fetches remote then caches`() = runTest(testDispatcher) {
        val dsMock = mock<MovieDataSource>()
        val daoMock = mock<TrendingMovieDao>()
        val repo = HomeRepositoryImpl(dsMock, daoMock)

        everySuspend { daoMock.getLastFetchedAt() } returns null

        everySuspend { dsMock.getTrending() } returns fakeResponse

        everySuspend { daoMock.clearAll() } returns Unit
        everySuspend {
            daoMock.insertAll(any())
        } returns Unit

        val out = repo.getTrending()

        verifySuspend(VerifyMode.exactly(0)) {
            daoMock.getAllEntities()
        }
        verifySuspend(VerifyMode.exactly(1)) {
            daoMock.getLastFetchedAt()
        }
        verifySuspend(VerifyMode.exactly(1)) {
            dsMock.getTrending()
        }
        assertEquals(fakeResponse.results.toHomeMovie(), out)
        assertEquals("", "")
    }

    @Test
    fun `valid cache returns local only`() = runTest(testDispatcher) {
        val dsMock = mock<MovieDataSource>()
        val daoMock = mock<TrendingMovieDao>()
        val repo = HomeRepositoryImpl(dsMock, daoMock)

        val nowMs = Clock.System.now().toEpochMilliseconds()
        everySuspend { daoMock.getLastFetchedAt() } returns nowMs

        val entity = TrendingMovieEntity(
            movie = fakeMovie,
            id = fakeMovie.id,
            fetchedAt = nowMs
        )
        everySuspend { daoMock.getAllEntities() } returns listOf(entity)

        val out = repo.getTrending()

        verifySuspend(VerifyMode.exactly(1)) { daoMock.getLastFetchedAt() }
        verifySuspend(VerifyMode.exactly(1)) { daoMock.getAllEntities() }
        verifySuspend(VerifyMode.exactly(0)) { dsMock.getTrending() }
        verifySuspend(VerifyMode.exactly(0)) { daoMock.clearAll() }
        verifySuspend(VerifyMode.exactly(0)) { daoMock.insertAll(any()) }

        assertEquals(listOf(fakeMovie).toHomeMovie(), out)
    }

    @Test
    fun `expired cache fetches remote and refreshes`() = runTest(testDispatcher) {
        val dsMock = mock<MovieDataSource>()
        val daoMock = mock<TrendingMovieDao>()
        val repo = HomeRepositoryImpl(dsMock, daoMock)

        val yesterday = Clock.System.now().toEpochMilliseconds() - 86_400_000L
        everySuspend { daoMock.getLastFetchedAt() } returns yesterday

        everySuspend { dsMock.getTrending() } returns fakeResponse

        everySuspend { daoMock.clearAll() } returns Unit
        everySuspend { daoMock.insertAll(any()) } returns Unit

        val out = repo.getTrending()

        verifySuspend(VerifyMode.exactly(1)) { daoMock.getLastFetchedAt() }
        verifySuspend(VerifyMode.exactly(0)) { daoMock.getAllEntities() }
        verifySuspend(VerifyMode.exactly(1)) { dsMock.getTrending() }
        verifySuspend(VerifyMode.exactly(1)) { daoMock.clearAll() }
        verifySuspend(VerifyMode.exactly(1)) {
            daoMock.insertAll(any())
        }

        assertEquals(fakeResponse.results.toHomeMovie(), out)
    }
}