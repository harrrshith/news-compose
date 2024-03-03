package com.harshith.news.ui.home
//For the initial scope I'm thinking of keeping news related to India. Categories - Sports, Tech, Cinema, Politics, and some other.


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.model.NewsArticle
import com.harshith.news.util.logE
import com.harshith.news.util.toNewArticleList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private var viewModelState = MutableStateFlow(
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
                    TAG.logE("${homeFeedNews.data.results}")
                    viewModelState.update { it.copy(
                        isLoading = false,
                        horizontalNewsFeed = homeFeedNews.data.results?.toNewArticleList()
                    ) }
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Exception -> {

                    TAG.logE("${homeFeedNews.e}")
                }
            }
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

    fun getNewsByCategory(category: String) {
//        "Sports", "Technology", "Entertainment", "Politics", "Others"
        viewModelScope.launch {
            when (category) {
                "Sports" -> {
                    getNewsArticlesByCategory(newsRepository.fetchNewsByCategory("sports"))
                }

                "Technology" -> {
                    TAG.logE("Technology")
                    getNewsArticlesByCategory(newsRepository.fetchNewsByCategory("technology"))
                }

                "Entertainment" -> {
                    TAG.logE("Entertainment")
                    getNewsArticlesByCategory(newsRepository.fetchNewsByCategory("entertainment"))
                }

                "Politics" -> {
                    getNewsArticlesByCategory(newsRepository.fetchNewsByCategory("politics"))
                }

                "Others" -> {
                    getNewsArticlesByCategory(newsRepository.fetchNewsByCategory("other"))
                }
            }
        }
    }

    private fun getNewsArticlesByCategory(result: NetworkResult<NetworkNewsResponse>) =  when(result){
        is NetworkResult.Success -> {
            viewModelState.update {
                it.copy(
                    verticalNewsFeed = result.data.results?.toNewArticleList()
                )
            }
        }

        is NetworkResult.Error -> {

        }

        is NetworkResult.Exception -> {

        }
    }
}