package com.harshith.news.ui.utils

import java.sql.Timestamp
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getFormattedTimeStamp(timeStamp: String): String{
    val timeFormatted = Instant.parse(timeStamp)
    val now = Instant.now()
    val duration = ChronoUnit.MINUTES.between(timeFormatted, now)
    val days = duration / (60 * 24)
    val hours = (duration % (60 * 24)) / 60
    val minutes = duration % 60
    return when{
        days == 0L && hours == 0L && minutes > 1 -> "$minutes minutes ago"
        days == 0L && hours == 0L && minutes <=1-> "$minutes minute ago"
        days == 0L && hours > 1L -> "$hours hours ago"
        days == 0L && hours <= 1L -> "$hours hour ago"
        days > 1 -> "$days days ago"
        days >= 1 -> "$days day ago"
        else -> "Just Now"
    }
}

