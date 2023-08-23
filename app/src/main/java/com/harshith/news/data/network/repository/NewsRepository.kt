package com.harshith.news.data.network.repository

import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.entities.NewsResponseNetworkEntity

interface NewsRepository {
    suspend fun fetchAllNews(): NetworkResult<NewsResponseNetworkEntity>
    suspend fun fetchTopHeadlines(country: String): NetworkResult<NewsResponseNetworkEntity>
    suspend fun fetchEntertainment(category: String): NetworkResult<NewsResponseNetworkEntity>
    suspend fun toggleFavourite(postId: String?)
}