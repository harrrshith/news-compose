package com.harshith.news.data.repository

import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle


interface NewsRepository {
    suspend fun fetchIndiaNews(country: String): NetworkResult<NetworkNewsResponse>

    suspend fun fetchNewsByCategory(category: String): NetworkResult<NetworkNewsResponse>

}