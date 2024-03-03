package com.harshith.news.model

import com.harshith.news.data.network.model.Source
import kotlinx.serialization.Serializable

@Serializable
data class NewsArticle(
    val content: String?,
    val creator: String?,
    val description: String?,
    val imageUrl: String?,
    val link: String?,
    val pubDate: String?,
    val title: String?,
    val sourceId: String?,
    val sourceName: String?
)
