package com.harshith.news.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "news_database")
data class NewsArticleEntity(
    @PrimaryKey
    val uuid: String = generateUUID(),
    var myCategory: String = getCategoryName(),
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    @Embedded val source: NewsSourceEntity?,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
){
    companion object{
        private const val category: String = ""
        fun generateUUID(): String{
            return UUID.randomUUID().toString()
        }
        fun getCategoryName() = category
    }
}

data class NewsSourceEntity(
    val id: String?,
    val name: String?
)