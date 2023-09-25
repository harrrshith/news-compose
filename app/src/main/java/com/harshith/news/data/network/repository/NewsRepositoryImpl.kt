package com.harshith.news.data.network.repository

import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.handleApi
import com.harshith.news.data.network.model.NetworkNewsArticle
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.util.toNewsArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService): NewsRepository {
    override suspend fun fetchAllNews(): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }
    override suspend fun fetchTopHeadlines(country: String): Flow<List<NewsArticleEntity>> {
        var news: List<NewsArticleEntity> = emptyList()
        when(val myResponse = handleApi { apiService.getTopHeadlines(country)}){
            is NetworkResult.Success -> news = myResponse.data.articles.map { it.toNewsArticleEntity(country) }
            is NetworkResult.Error -> ""
            is NetworkResult.Exception -> ""
        }
        return flowOf(news)
    }

    override suspend fun fetchEntertainment(category: String): NetworkResult<NetworkNewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavourite(postId: String?) {
        TODO()
    }
}