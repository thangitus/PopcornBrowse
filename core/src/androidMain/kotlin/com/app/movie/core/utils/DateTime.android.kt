package com.app.movie.core.utils

import io.github.aakira.napier.Napier
import java.text.SimpleDateFormat
import java.util.Locale

actual fun String.getFormattedDate(): String {
    val timestampFormat = "yyyy-MM-dd"
    val outputFormat = "dd/MM/yyyy"
    val local = Locale("en", "US")
    val dateFormatter = SimpleDateFormat(outputFormat, local)

    val parser = SimpleDateFormat(timestampFormat, local)

    runCatching {
        val date = parser.parse(this)

        if (date != null) {
            return dateFormatter.format(date)
        }
    }.onFailure {
        Napier.e("Error parsing date", it)
    }

    // If parsing fails, return the original timestamp
    return this
}

actual fun String.getYear(): Int {
    val timestampFormat = "yyyy-MM-dd"
    val local = Locale("en", "US")
    val parser = SimpleDateFormat(timestampFormat, local)

    return runCatching {
        val date = parser.parse(this)
        date?.let {
            it.year + 1900
        } ?: 0
    }.getOrElse {
        Napier.e("Error parsing year", it)
        0 // Return 0 if an error occurs
    }
}