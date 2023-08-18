package com.harshith.news.data.network

import android.util.Log
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkCall<T : Any>(
    private val data: Call<T>
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        data.enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = if(response.isSuccessful){
                    response.body()?.let {
                        return@let NetworkResult.Success(data = it)
                    }
                }else{
                    NetworkResult.Error(statusCode = response.code(), message = response.message())
                }
                callback.onResponse(
                    this@NetworkCall, Response.success(networkResult)
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
               //TODO:
                Log.e("Response", "${t.stackTrace}")
            }

        })
    }
    override fun clone(): Call<NetworkResult<T>> = NetworkCall(data.clone())

    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = data.isExecuted

    override fun cancel() = data.cancel()

    override fun isCanceled(): Boolean = data.isCanceled

    override fun request(): Request = data.request()

    override fun timeout(): Timeout = data.timeout()


}