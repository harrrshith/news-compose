package com.harshith.news.ui.home

import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.ErrorMessage

sealed interface HomeUiState{
    val isLoading: Boolean
    val errorMessage: String

    data class NoNews(
        override val isLoading: Boolean,
        override val errorMessage: String,
        val horizontalNewsFeed: List<NewsArticle>?,
        val verticalNewsFeed: List<NewsArticle>?
    ): HomeUiState

    data class HasNews(
        override val isLoading: Boolean,
        override val errorMessage: String = "",
        val horizontalNewsFeed: List<NewsArticle>?,
        val verticalNewsFeed: List<NewsArticle>?,
    ): HomeUiState
}