package com.harshith.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.local.entities.NewsSourceEntity

@Database(
    entities = [
        NewsArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsArticleDatabase : RoomDatabase(){
    abstract fun newsArticleDao(): NewsArticleDao
}