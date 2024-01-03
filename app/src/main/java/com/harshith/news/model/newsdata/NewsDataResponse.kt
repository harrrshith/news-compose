package com.harshith.news.model.newsdata

data class NewsArticle(
    val articleId: String,
    val category: List<String?>?,
    val content: String?,
    val country: List<String?>?,
    val creator: List<String?>?,
    val description: String?,
    val imageUrl: String?,
    val keywords: List<String?>?,
    val language: String?,
    val link: String?,
    val pubDate: String?,
    val sourceId: String?,
    val sourcePriority: Int?,
    val title: String?,
    val videoUrl: Any?
)