package com.harshith.news.ui.article

import androidx.lifecycle.ViewModel
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.NewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
// For now this `ArticleViewModel` handles light operation like booking a NewsArticle to read later
@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsArticleDao: NewsArticleDao
) : ViewModel() {

    fun saveNewsArticleToReadLater(newsArticle: NewsArticle){
        newsArticleDao.addNewsArticleToReadLater(newsArticle = newsArticle)
    }
}