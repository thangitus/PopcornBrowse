package com.app.movie.home.data.repository

import com.app.movie.core.data.local.dao.TrendingMovieDao
import com.app.movie.core.data.local.entity.TrendingMovieEntity
import com.app.movie.core.data.model.HomeMovie
import com.app.movie.core.domain.mapper.toHomeMovie
import com.app.movie.core.utils.isSameUtcDay
import com.app.movie.home.data.source.MovieDataSource
import com.app.movie.home.domain.repository.HomeRepository
import io.github.aakira.napier.Napier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


class HomeRepositoryImpl(
    private val movieDataSource: MovieDataSource,
    private val trendingMovieDao: TrendingMovieDao
) : HomeRepository {

    override suspend fun getTrending(): List<HomeMovie> {
        val last = trendingMovieDao.getLastFetchedAt()
        val useCache = last != null && isSameUtcDay(Instant.fromEpochMilliseconds(last))

        if (useCache) {
            return trendingMovieDao
                .getAllEntities()
                .map { it.movie }
                .toHomeMovie().also {
                    Napier.i("Using cached trending movies: ${it.size}")
                }
        }

        val resp = movieDataSource.getTrending()
        val now = Clock.System.now().toEpochMilliseconds()

        trendingMovieDao.clearAll()
        trendingMovieDao.insertAll(resp.results.map { m ->
            TrendingMovieEntity(id = m.id, movie = m, fetchedAt = now)
        })

        return resp.results.toHomeMovie()
    }
}
