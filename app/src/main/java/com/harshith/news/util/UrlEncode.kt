package com.harshith.news.util

import java.net.URLDecoder
import java.net.URLEncoder

fun String.toEncodedUrl(): String = URLEncoder.encode(this, "UTF-8")

fun String.toDecodedUrl(): String = URLDecoder.decode(this, "UTF-8")