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
    @SerializedName("articles")
    val results: List<NetworkNewsArticle>?,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)
data class NetworkNewsArticle(
    @SerializedName("content")
    val content: String?,
    @SerializedName("author")
    val creator: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("url")
    val link: String?,
    @SerializedName("publishedAt")
    val pubDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("source")
    val source: Source
)

data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
