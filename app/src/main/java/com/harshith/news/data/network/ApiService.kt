package com.harshith.news.data.network

import android.util.Log
import com.harshith.news.BuildConfig
import com.harshith.news.data.Result
import com.harshith.news.data.network.models.NetworkNewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//"https://newsapi.org/v2/top-headlines?country=in"
interface ApiService {
    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): Response<NetworkNewsResponse>


}

object RetrofitClientInstance{
    fun createInstance(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}