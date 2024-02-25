package com.harshith.news.model

import kotlinx.serialization.Serializable


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
    val videoUrl: String?
)
@Serializable
data class SerializedNewsArticle(
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
    val videoUrl: String?
)