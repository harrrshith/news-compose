package com.harshith.news.ui.home

import com.harshith.news.model.NewsArticle

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