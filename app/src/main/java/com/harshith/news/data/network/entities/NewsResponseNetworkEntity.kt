package com.harshith.news.data.network.entities

import com.google.gson.annotations.SerializedName


data class NewsResponseNetworkEntity(
    @SerializedName("articles")
    val articles: List<NewsArticleNetworkEntity>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class NewsArticleNetworkEntity(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: NewsSourceNetworkEntity,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

data class NewsSourceNetworkEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)