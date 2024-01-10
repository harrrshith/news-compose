package com.harshith.news.ui.home

import com.harshith.news.model.NewsFeed
import com.harshith.news.util.ErrorMessage

sealed interface HomeUiState{
    val isLoading: Boolean
    val errorMessage: List<ErrorMessage>

    data class NoNews(
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        val newsFeed: NewsFeed?
    ): HomeUiState

    data class HasNews(
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        val newsFeed: NewsFeed?,
    ): HomeUiState
}