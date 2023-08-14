package com.harshith.news.data.network.models

import com.google.gson.annotations.SerializedName


data class NetworkNewsResponse(
    @SerializedName("articles")
    val articles: List<NetworkArticle>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class NetworkArticle(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: NetworkSource,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

data class NetworkSource(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)