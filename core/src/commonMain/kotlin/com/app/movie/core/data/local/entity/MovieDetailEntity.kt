package com.app.movie.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.core.data.model.movie_detail.MovieDetailResponse

@Entity(tableName = "detail_movies")
data class MovieDetailEntity(
    @Embedded(prefix = "detail_")
    val detail: MovieDetailResponse,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = detail.id,
)
