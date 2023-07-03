package com.harshith.news.data

sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error<out T>(val exception: Exception): Result<Nothing>()
}

fun <T> Result<T>.successOr(fallback: T): T{
    return (this as? Result.Success<T>)?.data ?: fallback
}