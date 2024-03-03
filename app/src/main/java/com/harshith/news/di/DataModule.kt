package com.harshith.news.di

import android.content.Context
import androidx.room.Room
import com.harshith.news.data.local.NewsArticleDatabase
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.network.ApiService
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideNewsService(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): NewsArticleDatabase = Room.databaseBuilder(
        context,
        NewsArticleDatabase::class.java,
        "news_database"
    ).build()

    @Provides
    fun providesNewsDao(newDatabase: NewsArticleDatabase): NewsArticleDao = newDatabase.newsArticleDao()
}

/*

object RetrofitClientInstance{
    fun createInstance(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
 */