package com.harshith.news.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.NewsArticle
import com.harshith.news.util.toNewsArticleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
// For now this `ArticleViewModel` handles light operation like booking a NewsArticle to read later
@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsArticleDao: NewsArticleDao
) : ViewModel() {

    fun saveNewsArticleToReadLater(newsArticle: NewsArticle){
        viewModelScope.launch {
            newsArticleDao.addNewsArticleToReadLater(newsArticleEntity = newsArticle.toNewsArticleEntity())
        }
    }
}