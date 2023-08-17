package com.harshith.news.data.network

import android.util.Log
import com.harshith.news.BuildConfig
import com.harshith.news.data.network.models.NetworkNewsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//"https://newsapi.org/v2/top-headlines?country=in"
private const val API_KEY = BuildConfig.API_KEY
interface ApiService {
    @Headers("X-Api-Key:$API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): Response<NetworkNewsResponse>
}

object RetrofitClientInstance{
    fun createInstance(): ApiService{
        Log.e("Response", "Helloooo")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
