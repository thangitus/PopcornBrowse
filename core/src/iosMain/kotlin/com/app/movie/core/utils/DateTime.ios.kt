package com.app.movie.core.utils

actual fun String.getYear(): Int {
    return runCatching {
        val year = this.substring(0, 4).toInt()
        year
    }.getOrElse {
        0 // Return 0 if an error occurs
    }
}