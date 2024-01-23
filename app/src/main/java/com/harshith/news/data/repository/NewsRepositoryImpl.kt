package com.harshith.news.data.repository

import android.accounts.NetworkErrorException
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle
import com.harshith.news.util.logE
import com.harshith.news.util.logI
import com.harshith.news.util.logV
import java.lang.Exception
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchIndiaNews(country: String, language: String): NetworkNewsResponse {
        return apiService.fetchIndiaNews(country, language).body()!!
    }

}