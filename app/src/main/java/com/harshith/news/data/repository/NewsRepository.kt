package com.harshith.news.data.repository

import com.harshith.news.data.network.model.NetworkNewsResponse


interface NewsRepository {
    suspend fun fetchIndiaNews(country: String): NetworkNewsResponse

    suspend fun fetchFirstNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchSecondNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchThirdNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchFourthNewsCategory(category: String): NetworkNewsResponse

    suspend fun fetchFifthNewsCategory(category: String): NetworkNewsResponse
}