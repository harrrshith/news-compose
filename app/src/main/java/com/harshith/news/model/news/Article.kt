package com.harshith.news.model.news

import java.util.UUID

data class Article(
    val uuid: String = generateId(),
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source?,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
){
    companion object{
        fun generateId(): String{
            return UUID.randomUUID().toString()
        }
    }
}