package com.harshith.news.data.repository

import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.model.NetworkNewsResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchIndiaNews(country: String, language: String): NetworkNewsResponse {
        return apiService.fetchIndiaNews(country, language).body()!!
    }

}