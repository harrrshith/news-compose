package com.harshith.news.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "news_database")
data class NewsArticleEntity(
    @PrimaryKey
    val uuid: String = generateUUID(),
    val content: String? = null,
    val creator: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val link: String? = null,
    val pubDate: String? = null,
    val title: String? = null,
    val sourceId: String? = null,
    val sourceName: String? = null
){
    companion object{
        fun generateUUID(): String{
            return UUID.randomUUID().toString()
        }
    }
}