package com.harshith.news.data.repository

import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.util.logE
import com.harshith.news.util.logV
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchIndiaNews(country: String, language: String): NetworkNewsResponse {
        "PRINT".logE("Hello")
        val result = apiService.fetchIndiaNews(country, language).body()!!
        "RESULT".logV("$result")
        return result
    }

}