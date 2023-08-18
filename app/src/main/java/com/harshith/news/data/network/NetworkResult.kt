package com.harshith.news.data.network

sealed class NetworkResult<T: Any> {
    class Success<T: Any>(val data: T): NetworkResult<T>()
    class Error<T: Any>(val statusCode: Int, val message: String): NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable): NetworkResult<T>()
}
/**
sealed interface ApiResult<T: Any>
class ApiSuccess<T: Any>(val data: T): ApiResult<T>
class ApiError<T: Any>(val statusCode: Int, val message: String): ApiResult<T>
class ApiException<T: Any>(val e: Throwable): ApiResult<T>
 */
