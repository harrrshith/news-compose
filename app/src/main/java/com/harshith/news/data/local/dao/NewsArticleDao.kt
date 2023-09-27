package com.harshith.news.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.harshith.news.data.local.entities.NewsArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao{

    @Upsert
    fun addNewsArticles(articles: List<NewsArticleEntity>)

    @Query("SELECT * FROM news_database WHERE myCategory=:category") // No idea why category is category$1
    fun observeByCategory(category: String): Flow<List<NewsArticleEntity>>

    @Query("SELECT * FROM news_database WHERE id=:uuid")
    fun getANewsArticle(uuid: String): NewsArticleEntity

    @Query("SELECT * FROM news_database")
    suspend fun getAllNewsArticles(): List<NewsArticleEntity>

    @Query("DELETE FROM news_database")
    fun deleteAllNews()
}