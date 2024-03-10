package com.harshith.news.util

val regex = Regex("\\[.+?\\]")

fun String.addRegex(): String = this.replace(regex, "")