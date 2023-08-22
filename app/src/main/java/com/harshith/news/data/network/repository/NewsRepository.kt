package com.harshith.news.data.network.repository

import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.models.NetworkNewsResponse
import dagger.Binds

interface NewsRepository {
    suspend fun fetchAllNews(): NetworkResult<NetworkNewsResponse>
    suspend fun fetchTopHeadlines(country: String): NetworkResult<NetworkNewsResponse>
    suspend fun fetchEntertainment(category: String): NetworkResult<NetworkNewsResponse>
    suspend fun toggleFavourite(postId: String?)
}