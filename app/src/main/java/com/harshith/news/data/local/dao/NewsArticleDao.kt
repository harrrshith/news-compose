package com.harshith.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.harshith.news.data.local.entities.NewsArticleEntity

@Dao
interface NewsArticleDao{

    @Insert
    fun addNewsArticles(articles: List<NewsArticleEntity>)

//    @Query("SELECT * FROM news_database WHERE myCategory='${Constants.INDIA_NEWS}'")
//    fun observerIndiaCategory(): Flow<List<NewsArticleEntity>>
//
//    @Query("SELECT * FROM news_database WHERE myCategory='${Constants.USA_NEWS}'")
//    fun observerUsaCategory(): Flow<List<NewsArticleEntity>>

    @Query("SELECT * FROM news_database WHERE uuid=:uuid")
    fun getANewsArticle(uuid: String): NewsArticleEntity

    //@Query("SELECT * FROM news_database")
    //suspend fun getAllNewsArticles(): List<NewsArticleEntity>

    @Query("DELETE FROM news_database")
    fun deleteAllNews()

    @Query("SELECT COUNT(*) FROM NEWS_DATABASE")
    fun getDatabaseCount(): Int

    @Insert
    suspend fun addNewsArticleToReadLater(newsArticleEntity: NewsArticleEntity)
}