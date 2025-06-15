package com.app.movie.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.movie.core.data.local.entity.TrendingMovieEntity

@Dao
interface TrendingMovieDao {
    @Query("SELECT * FROM trending_movies")
    suspend fun getAllEntities(): List<TrendingMovieEntity>

    @Query(
        """
    SELECT fetched_at 
    FROM trending_movies 
    ORDER BY fetched_at DESC 
    LIMIT 1
  """
    )
    suspend fun getLastFetchedAt(): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TrendingMovieEntity>)

    @Query("DELETE FROM trending_movies")
    suspend fun clearAll()
}