package com.harshith.news.data.repository

import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle


interface NewsRepository {
    suspend fun fetchIndiaNews(country: String): NetworkResult<NetworkNewsResponse>

    suspend fun fetchFirstNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchSecondNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchThirdNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchFourthNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchFifthNewsCategory(category: String): NetworkNewsResponse
}