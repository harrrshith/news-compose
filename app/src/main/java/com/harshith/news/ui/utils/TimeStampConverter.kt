package com.harshith.news.ui.utils

import java.sql.Timestamp
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getFormattedTimeStamp(timeStamp: String): String{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val currentTime = Timestamp(System.currentTimeMillis()).toString().split(".")[0]
    val currentDateFormatted = LocalDateTime.parse(currentTime, formatter)
    val newsDateFormatted = LocalDateTime.parse(timeStamp, formatter)
    val duration = Duration.between(newsDateFormatted, currentDateFormatted)
    val days = duration.toDays().toInt()
    val hours = duration.toHours().toInt()
    val minutes = duration.toMinutes().toInt()
    return when{
        days == 0 && hours == 0 && minutes > 1 -> "$minutes minutes ago"
        days == 0 && hours == 0 && minutes <=1-> "$minutes minute ago"
        days == 0 && hours > 1 -> "$hours hours ago"
        days == 0 && hours <= 1 -> "$hours hour ago"
        days > 1 -> "$days days ago"
        days >= 1 -> "$days day ago"
        else -> "Just Now"
    }
}