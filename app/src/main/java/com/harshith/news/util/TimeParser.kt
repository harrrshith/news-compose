package com.harshith.news.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun parseTime(isoString: String): String{
    if(isoString.isEmpty())
        return ""
    val dateTime = LocalDateTime.parse(isoString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm"))
}