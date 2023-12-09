package com.harshith.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.newsdata.NewsArticle
import com.harshith.news.util.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HomeViewModelState(
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