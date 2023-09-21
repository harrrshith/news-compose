package com.harshith.news.data.network.repository

import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.handleApi
import com.harshith.news.data.network.model.NetworkNewsResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService): NewsRepository {
    override suspend fun fetchAllNews(): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }
    override suspend fun fetchTopHeadlines(country: String): NetworkResult<NetworkNewsResponse> {
        return handleApi { apiService.getTopHeadlines(country)}
    }

    override suspend fun fetchEntertainment(category: String): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavourite(postId: String?) {
        TODO()
    }
}