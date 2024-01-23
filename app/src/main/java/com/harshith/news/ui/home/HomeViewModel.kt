package com.harshith.news.ui.home
//For the initial scope I'm thinking of keeping news related to India. Categories - Sports, Tech, Cinema, Politics, and some other.


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.logV
import com.harshith.news.util.ErrorMessage
import com.harshith.news.util.logE
import com.harshith.news.util.toNewArticleList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HomeViewModelState(
    val isLoading: Boolean = false,
    val newsFeed: NewsFeed? = null,
    val errorMessage: List<ErrorMessage> = emptyList()
){
    fun toUiState(): HomeUiState =
        if (newsFeed == null) {
            HomeUiState.NoNews(
                isLoading = isLoading,
                errorMessage = errorMessage,
                newsFeed = NewsFeed(emptyList())
            )
        }else{
            HomeUiState.HasNews(
                isLoading = isLoading,
                newsFeed = newsFeed,
                errorMessage = errorMessage
            )
        }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val TAG = "HomeViewModel"
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true
        )
    )
    fun printClassName(){
        HomeViewModel::class.java.simpleName
    }

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    init {
        viewModelScope.launch {
            val homeFeedNews = newsRepository.fetchIndiaNews(
                "in",
                "en").results!!.toNewArticleList()
            TAG.logE("$homeFeedNews")
            viewModelState.update { it.copy(
                isLoading = false,
                newsFeed = NewsFeed(
                    homeFeedNews = homeFeedNews
                )
            ) }
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