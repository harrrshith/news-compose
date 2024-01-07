package com.harshith.news.util

import android.util.Log

fun String.logE(message: String) = Log.e(this, message)

fun String.logV(message: String) = Log.v(this, message)

fun String.logW(message: String) = Log.w(this, message)

fun String.logI(message: String) = Log.i(this, message)

fun String.logWtf(message: String) = Log.wtf(this, message)
