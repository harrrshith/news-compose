package com.harshith.news.data.network.repository

import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    var indiaNewsResponse:  Flow<List<NewsArticleEntity>>
    suspend fun fetchAllNews(): NetworkResult<NetworkNewsResponse>
    suspend fun fetchTopHeadlines(country: String): Flow<List<NewsArticleEntity>>
    suspend fun fetchEntertainment(category: String): NetworkResult<NetworkNewsResponse>
    suspend fun toggleFavourite(postId: String?)
}