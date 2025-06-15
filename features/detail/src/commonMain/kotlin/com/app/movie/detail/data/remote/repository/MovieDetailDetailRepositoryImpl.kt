package com.app.movie.detail.data.remote.repository

import com.app.movie.core.data.local.dao.MovieDetailDao
import com.app.movie.core.data.local.entity.MovieDetailEntity
import com.app.movie.detail.data.remote.datasource.DetailMovieDatasource
import com.app.movie.detail.domain.remote.mapper.toUiModel
import com.app.movie.detail.domain.remote.model.MovieDetail
import com.app.movie.detail.domain.remote.repository.MovieDetailRepository
import io.github.aakira.napier.Napier

class MovieDetailDetailRepositoryImpl(
    private val remote: DetailMovieDatasource,
    private val local: MovieDetailDao

) : MovieDetailRepository {
    override suspend fun getDetails(id: Long): MovieDetail {
        val localDetail = local.getMovieDetail(id)
        if (localDetail != null) {
            Napier.i("Using cached movie detail for id: $id")
            return localDetail.detail.toUiModel()
        }

        val response = remote.getDetails(id)
        val detailEntity = MovieDetailEntity(detail = response)
        local.insert(detailEntity)
        return response.toUiModel()
    }

}