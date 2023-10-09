package com.harshith.news.ui.article

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.harshith.news.data.previewData.article
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.news.Article
import com.harshith.news.util.toNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArticleUiState(
    val article: Article,
    val isFavourite: Boolean = false
)

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(ArticleUiState(
        article = article,
        isFavourite = false
    ))
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()
    private val postId: String = checkNotNull(savedStateHandle["postId"])

    init {
        fetchNewsArticle()
    }
    private fun fetchNewsArticle(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _uiState.update {
                    it.copy(
                        article = newsRepository.getAnArticle(postId).toNewsArticle(),
                        isFavourite = false
                    )
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}