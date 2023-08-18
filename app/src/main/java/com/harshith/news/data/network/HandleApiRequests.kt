package com.harshith.news.data.network

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T: Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T>{
    return try{
        val response = execute()
        val body = response.body()
        if(response.isSuccessful && body != null){
            NetworkResult.Success(body)
        }else{
            NetworkResult.Error(statusCode = response.code(), message = response.message())
        }
    }catch (e: HttpException){
        NetworkResult.Exception(e)
    }catch (e: Throwable){
        NetworkResult.Exception(e)
    }
}