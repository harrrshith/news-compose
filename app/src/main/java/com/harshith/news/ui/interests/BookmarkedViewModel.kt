package com.harshith.news.ui.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.local.dao.NewsArticleDao
import com.harshith.news.model.NewsArticle
import com.harshith.news.util.toNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BookmarkedUiState(
    val newsArticles: List<NewsArticle>? = null,
    val isLoading: Boolean = false,
    val error: String? = ""
)
const val TAG = "InterestsViewModel"
@HiltViewModel
class BookmarkedViewModel @Inject constructor(
    private val newsArticleDao: NewsArticleDao
): ViewModel() {
    private val articleUiState = MutableStateFlow(
        BookmarkedUiState(
            isLoading = true
        )
    )
    val uiState = articleUiState.asStateFlow()
    init {
        getAllBookmarkedNewsArticle()
    }
    private fun getAllBookmarkedNewsArticle(){
        viewModelScope.launch {
            val newsArticles: List<NewsArticle> = newsArticleDao.getAllBookMarkedNewsArticle().toNewsArticle()
            articleUiState.update {
                it.copy(
                    newsArticles = newsArticles
                )
            }
        }
    }
}
