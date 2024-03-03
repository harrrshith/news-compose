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

interface ApiService {
    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("top-headlines")
    suspend fun fetchIndiaNews(
        @Query("country") country: String,
        @Query("language") language: String = "en"
    ): Response<NetworkNewsResponse>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("everything")
    suspend fun fetchNewsFirstNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String
    ): Response<NetworkNewsResponse>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("everything")
    suspend fun fetchNewsSecondNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("everything")
    suspend fun fetchNewsThirdNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("everything")
    suspend fun fetchNewsFourthNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>

    @Headers("X-Api-Key:${BuildConfig.API_KEY}")
    @GET("everything")
    suspend fun fetchNewsFifthNewsCategory(
        @Query("country") country: String = "in",
        @Query("language") language: String = "en",
        @Query("category") category: String,
    ): Response<NetworkNewsResponse>
}
