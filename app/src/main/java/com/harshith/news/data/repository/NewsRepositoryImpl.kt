package com.harshith.news.data.repository

import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.ApiService
import com.harshith.news.util.Constants
import com.harshith.news.util.toNewsArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
    override fun getAnArticle(postId: String): NewsArticleEntity {
        return newsArticleDao.getANewsArticle(postId)
    }

    private suspend fun getNewsFromApi(category: String) = apiService.getTopHeadlines(category).body()!!.articles.map { it.toNewsArticleEntity(category) }
}