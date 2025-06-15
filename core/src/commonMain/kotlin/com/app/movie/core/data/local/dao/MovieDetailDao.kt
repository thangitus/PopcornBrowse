package com.app.movie.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.core.data.local.entity.MovieDetailEntity

@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM detail_movies WHERE id = :id")
    suspend fun getMovieDetail(id: Long): MovieDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: MovieDetailEntity)

    @Query("DELETE FROM detail_movies WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM detail_movies")
    suspend fun clearAll()
}