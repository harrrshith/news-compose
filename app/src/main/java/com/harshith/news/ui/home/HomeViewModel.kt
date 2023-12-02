package com.harshith.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.news.Article
import com.harshith.news.model.newsdata.NewsArticle
import com.harshith.news.util.Constants
import com.harshith.news.util.ErrorMessage
import com.harshith.news.util.toNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

private data class HomeViewModelState(
    val newsArticle: NewsArticle? = null,
    val selectedPostId: String? = null,
    val isArticleOpen: Boolean = false,
    val favourites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: List<ErrorMessage> = emptyList(),
    val searchInput: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true
        )
    )

    val uiState = viewModelState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        viewModelState.value.isLoading
    )


    init {
        viewModelScope.launch {

        }
    }

    fun toggleFavourites(postId: String?){

    }

    fun selectArticle(postId: String?){
        interactWithArticleDetails(postId)
    }

    fun onSearchInputChanged(searchInput: String){

    }

    fun interactWithFeed(){

    }

    fun interactWithArticleDetails(postId: String?){

    }
    private suspend fun getIndiaNews() = withContext(Dispatchers.IO) {

    }
}