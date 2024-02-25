package com.harshith.news.util

import java.net.URLEncoder

fun String.toEncodedUrl(): String = URLEncoder.encode(this, "UTF-8")