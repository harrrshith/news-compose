package com.harshith.news.data.network.model

import com.google.gson.annotations.SerializedName


/**
data class NetworkNewsResponse(
    @SerializedName("articles")
    val articles: List<NetworkNewsArticle>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class NetworkNewsArticle(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: NetworkNewsSource,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

data class NetworkNewsSource(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
 */

data class NetworkNewsResponse(
    @SerializedName("nextPage")
    val nextPage: String?,
    @SerializedName("results")
    val results: List<NetworkNewsArticle>?,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
data class NetworkNewsArticle(
    @SerializedName("article_id")
    val articleId: String,
    @SerializedName("category")
    val category: List<String?>?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("country")
    val country: List<String?>?,
    @SerializedName("country")
    val creator: List<String?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("keywords")
    val keywords: List<String?>?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("pubDate")
    val pubDate: String?,
    @SerializedName("source_id")
    val sourceId: String?,
    @SerializedName("source_priority")
    val sourcePriority: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video_url")
    val videoUrl: Any?
)