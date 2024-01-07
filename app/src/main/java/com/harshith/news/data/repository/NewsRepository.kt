package com.harshith.news.data.repository

import com.harshith.news.data.network.model.NetworkNewsResponse


interface NewsRepository {
    suspend fun fetchIndiaNews(country: String, language: String): NetworkNewsResponse
}