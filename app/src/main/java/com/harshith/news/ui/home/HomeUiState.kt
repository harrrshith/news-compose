package com.harshith.news.ui.home

import com.harshith.news.model.NewsArticle

sealed interface HomeUiState{
    val isHorizontalLoading: Boolean
    val isVerticalLoading: Boolean
    val errorMessage: String

    data class NoNews(
        override val errorMessage: String,
        val horizontalNewsFeed: List<NewsArticle>?,
        val verticalNewsFeed: List<NewsArticle>?,
        override val isHorizontalLoading: Boolean,
        override val isVerticalLoading: Boolean
    ): HomeUiState

    data class HasNews(
        override val errorMessage: String = "",
        val horizontalNewsFeed: List<NewsArticle>?,
        val verticalNewsFeed: List<NewsArticle>?,
        override val isHorizontalLoading: Boolean,
        override val isVerticalLoading: Boolean,
    ): HomeUiState
}