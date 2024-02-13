package com.harshith.news.data.network

import com.harshith.news.BuildConfig
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//"https://newsapi.org/v2/top-headlines?country=in"
interface ApiService {
    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchIndiaNews(
        @Query("country") country: String,
        @Query("language") language: String = "en"
    ): Response<NetworkNewsResponse>

    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchNewsFirstNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String
    ): Response<NetworkNewsResponse>

    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchNewsSecondNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchNewsThirdNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchNewsFourthNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-ACCESS-KEY:${BuildConfig.API_KEY}")
    @GET("api/1/news")
    suspend fun fetchNewsFifthNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>
}
