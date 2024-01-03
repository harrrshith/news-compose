package com.harshith.news.data.repository

import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.news.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun fetchIndiaNews(country: String, language: String): NetworkNewsResponse
}