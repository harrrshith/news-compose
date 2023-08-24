package com.harshith.news.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun parseTime(time: String): String{
    if(time.isEmpty())
        return ""
    val dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyy - HH:mm"))
}