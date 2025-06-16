package com.app.movie.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.movie.core.data.local.dao.MovieDetailDao
import com.app.movie.core.data.local.dao.TrendingMovieDao
import com.app.movie.core.data.local.entity.MovieDetailEntity
import com.app.movie.core.data.local.entity.TrendingMovieEntity

@Database(
    entities = [TrendingMovieEntity::class, MovieDetailEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingMovieDao

    abstract fun movieDetailDao(): MovieDetailDao
}