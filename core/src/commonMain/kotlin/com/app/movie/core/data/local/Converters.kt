package com.app.movie.core.data.local


import androidx.room.TypeConverter
import com.app.movie.core.data.model.movie_detail.MovieGenre
import com.app.movie.core.data.model.movie_detail.ProductionCompany
import com.app.movie.core.data.model.movie_detail.ProductionCountry
import com.app.movie.core.data.model.movie_detail.SpokenLanguage
import kotlinx.serialization.json.Json

object Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromGenreIds(value: List<Int>): String =
        json.encodeToString(value)

    @TypeConverter
    fun toGenreIds(value: String): List<Int> =
        json.decodeFromString(value)

    @TypeConverter
    fun fromGenreList(value: List<MovieGenre>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toGenreList(value: String): List<MovieGenre> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromProductionCompany(value: List<ProductionCompany>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toProductionCompany(value: String): List<ProductionCompany> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromProductionCountry(value: List<ProductionCountry>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toProductionCountry(value: String): List<ProductionCountry> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSpokenLanguage(value: List<SpokenLanguage>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toSpokenLanguage(value: String): List<SpokenLanguage> {
        return Json.decodeFromString(value)
    }

}
