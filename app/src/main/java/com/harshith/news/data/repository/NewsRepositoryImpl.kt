package com.harshith.news.data.repository

import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.handleApi
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle
import com.harshith.news.util.logE
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchIndiaNews(country: String): NetworkResult<NetworkNewsResponse> {
        return handleApi { apiService.fetchIndiaNews(country = country)}
    }

    override suspend fun fetchNewsByCategory(category: String): NetworkResult<NetworkNewsResponse> {
        return handleApi { apiService.fetchNewsFirstNewsCategory(category = category) }
    }

}