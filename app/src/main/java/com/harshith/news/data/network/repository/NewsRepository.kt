package com.harshith.news.data.network.repository

import com.harshith.news.data.Result
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.RetrofitClientInstance
import com.harshith.news.data.network.handleApi
import com.harshith.news.data.network.models.NetworkNewsResponse
import retrofit2.Response

class NewsRepository {
    suspend fun fetchTopHeadlines(country: String): NetworkResult<NetworkNewsResponse>{
        val apiService: ApiService = RetrofitClientInstance.createInstance()
        return handleApi { apiService.getTopHeadlines(country) }
    }
}