package com.harshith.news.data.repository

import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.model.NetworkNewsResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchIndiaNews(country: String): NetworkNewsResponse {
        return apiService.fetchIndiaNews(country).body()!!
    }

    override suspend fun fetchFirstNewsCategory(category: String): NetworkNewsResponse {
        return apiService.fetchNewsFirstNewsCategory(category = category).body()!!
    }

    override suspend fun fetchSecondNewsCategory(category: String): NetworkNewsResponse {
        return apiService.fetchNewsSecondNewsCategory(category = category).body()!!
    }

    override suspend fun fetchThirdNewsCategory(category: String): NetworkNewsResponse {
        return apiService.fetchNewsThirdNewsCategory(category = category).body()!!
    }

    override suspend fun fetchFourthNewsCategory(category: String): NetworkNewsResponse {
        return apiService.fetchNewsFourthNewsCategory(category = category).body()!!
    }

    override suspend fun fetchFifthNewsCategory(category: String): NetworkNewsResponse {
        return apiService.fetchNewsFifthNewsCategory(category = category).body()!!
    }


}