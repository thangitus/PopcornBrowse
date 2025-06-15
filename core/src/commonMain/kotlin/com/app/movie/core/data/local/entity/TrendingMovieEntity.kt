package com.app.movie.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.movie.core.data.model.movie.Movie

@Entity(tableName = "trending_movies")
data class TrendingMovieEntity(
    @Embedded(prefix = "movie_")
    val movie: Movie,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = movie.id,

    @ColumnInfo(name = "fetched_at")
    val fetchedAt: Long
)