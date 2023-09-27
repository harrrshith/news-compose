package com.harshith.news.data.network.repository

import android.util.Log
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.util.toNewsArticleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    override suspend fun fetchAllNews(): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }
    override suspend fun fetchTopHeadlines(country: String): Flow<List<NewsArticleEntity>> = flow{
        val news = apiService.getTopHeadlines(country).body()?.articles?.map { it.toNewsArticleEntity(country) }!!
        CoroutineScope(Dispatchers.IO).launch {
            newsArticleDao.deleteAllNews()
            newsArticleDao.addNewsArticles(news)
            val article = newsArticleDao.getANewsArticle(news[0].uuid)
            Log.e("Response", "$article")
            Log.e("ResponseNews", "${newsArticleDao.getAllNewsArticles()}")
        }
        emit(newsArticleDao.getAllNewsArticles())
    }

    override suspend fun fetchEntertainment(category: String): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavourite(postId: String?) {
        TODO()
    }
}