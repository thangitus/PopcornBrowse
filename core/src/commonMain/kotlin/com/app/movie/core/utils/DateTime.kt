package com.app.movie.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

expect fun String.getFormattedDate(): String
expect fun String.getYear(): Int

fun isSameUtcDay(lastFetched: Instant): Boolean {
    val lastDate = lastFetched.toLocalDateTime(TimeZone.UTC).date
    val todayDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
    return lastDate == todayDate
}