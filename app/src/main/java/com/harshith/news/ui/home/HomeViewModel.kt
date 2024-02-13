package com.harshith.news.ui.home
//For the initial scope I'm thinking of keeping news related to India. Categories - Sports, Tech, Cinema, Politics, and some other.


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.logV
import com.harshith.news.util.ErrorMessage
import com.harshith.news.util.logE
import com.harshith.news.util.toNewArticleList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
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
    val horizontalNewsFeed: List<NewsArticle>? = null,
    val verticalNewsFeed: List<NewsArticle>? = null,
    val errorMessage: String = ""
){
    fun toUiState(): HomeUiState =
        if (horizontalNewsFeed == null) {
            HomeUiState.NoNews(
                isLoading = isLoading,
                errorMessage = errorMessage,
                verticalNewsFeed = emptyList(),
                horizontalNewsFeed = emptyList()
            )
        }else{
            TAG.logE("HARSHITH")
            HomeUiState.HasNews(
                isLoading = isLoading,
                errorMessage = errorMessage,
                verticalNewsFeed = verticalNewsFeed,
                horizontalNewsFeed = horizontalNewsFeed
            )
        }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val TAG = "HomeViewModel"

    private lateinit var firstCategory: List<NewsArticle>
    private lateinit var secondCategory: List<NewsArticle>
    private lateinit var thirdCategory: List<NewsArticle>
    private lateinit var fourthCategory: List<NewsArticle>
    private lateinit var fifthCategory: List<NewsArticle>
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true
        )
    )

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    init {
        viewModelScope.launch {
            when(val homeFeedNews = newsRepository.fetchIndiaNews("in")){
                is NetworkResult.Success -> {
                    viewModelState.update { it.copy(
                        isLoading = false,
                        horizontalNewsFeed = homeFeedNews.data.results?.toNewArticleList()
                    ) }
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Exception -> {

                }
            }
            getCategorisedNews()
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

    suspend fun getCategorisedNews(){
//        "Sports", "Technology", "Entertainment", "Politics", "Others"
        when(val category1 = newsRepository.fetchFirstNewsCategory("sports")){
            is NetworkResult.Success -> {
                viewModelState.update {it.copy(
                    verticalNewsFeed = category1.data.results?.toNewArticleList()
                ) }
            }
            is NetworkResult.Error -> {

            }
            is NetworkResult.Exception -> {

            }
        }
    }
}