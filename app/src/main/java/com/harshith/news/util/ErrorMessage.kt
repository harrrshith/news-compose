package com.harshith.news.util

import androidx.annotation.StringRes

data class ErrorMessage(
    val id: Long,
    @StringRes val messageId: Int
)
