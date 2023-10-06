package com.harshith.news.data.repository

import android.util.Log
import com.google.gson.Gson
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.local.entities.NewsSourceEntity
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.data.previewData.article
import com.harshith.news.model.news.Article
import com.harshith.news.util.Constants
import com.harshith.news.util.toNewsArticle
import com.harshith.news.util.toNewsArticleEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsArticleDao: NewsArticleDao,
): NewsRepository {
    private fun deleteDatabaseEntries() {
        newsArticleDao.deleteAllNews()
    }

    override fun fetchIndiaNews(category: String): Flow<List<NewsArticleEntity>>{
        return newsArticleDao.observerIndiaCategory()
    }

    override fun fetchUsaNews(category: String): Flow<List<NewsArticleEntity>>{
        return newsArticleDao.observerUsaCategory()
    }

    override suspend fun fetchAllNews() {
        val indiaNews = getNewsFromApi(Constants.INDIA_NEWS)
        val usaNews = getNewsFromApi(Constants.USA_NEWS)
        deleteDatabaseEntries()
        newsArticleDao.addNewsArticles(indiaNews)
        newsArticleDao.addNewsArticles(usaNews)
    }

    override fun getDatabaseCount(): Int = newsArticleDao.getDatabaseCount()

    private suspend fun getNewsFromApi(category: String) = apiService.getTopHeadlines(category).body()!!.articles.map { it.toNewsArticleEntity(category) }
}