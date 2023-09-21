package com.harshith.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.model.NetworkNewsArticle
import com.harshith.news.data.network.repository.NewsRepository
import com.harshith.news.model.news.Article
import com.harshith.news.util.ErrorMessage
import com.harshith.news.util.toNewsArticleEntity
import com.harshith.news.util.toUiArticleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

sealed interface HomeUiState{
    val isLoading: Boolean
    val errorMessage: List<ErrorMessage>
    val searchInput: String

    data class NoPosts(
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        override val searchInput: String
    ): HomeUiState

    data class HasPosts(
        val newsFeed: NewsFeed?,
        val selectedPost: Boolean,
        val isArticleOpen: Boolean,
        val favourites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        override val searchInput: String
    ): HomeUiState

}

private data class HomeViewModelState(
    val newsFeed: NewsFeed? = null,
    val selectedPostId: String? = null,
    val isArticleOpen: Boolean = false,
    val favourites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: List<ErrorMessage> = emptyList(),
    val searchInput: String = ""
){
    fun toUiState(): HomeUiState =
        if(newsFeed == null){
            HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessage = errorMessage,
                searchInput = searchInput
            )
        }else{
            HomeUiState.HasPosts(
                newsFeed = newsFeed,
                selectedPost = false,
                isArticleOpen = false,
                favourites = favourites,
                isLoading = isLoading,
                errorMessage = errorMessage,
                searchInput = searchInput
            )
        }
}

data class NewsFeed(
    val highlightedNews: Article,
    val recommendedNews: List<Article>,
    val popularNews: List<Article>,
    val recentNews: List<Article>
){
    val allFeedNews: List<Article> = listOf(highlightedNews) + recommendedNews + popularNews + recentNews
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val preSelectedPostId: String = ""
    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
            selectedPostId = preSelectedPostId,
            isArticleOpen = false
        )
    )

    val uiState = viewModelState
        .map( HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    init {
        getSomeResponse()
    }

    fun toggleFavourites(postId: String?){
        viewModelScope.launch {
            newsRepository.toggleFavourite(postId)
        }
    }

    fun selectArticle(postId: String?){
        interactWithArticleDetails(postId)
    }

    fun onSearchInputChanged(searchInput: String){
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    fun interactWithFeed(){
        viewModelState.update {
            it.copy(isArticleOpen = false)
        }
    }

    fun interactWithArticleDetails(postId: String?){
        viewModelState.update {
            it.copy(
                selectedPostId = postId,
                isArticleOpen = true
            )
        }
    }
    private fun getSomeResponse(){
        var indiaResponse: List<NetworkNewsArticle> = emptyList()
        var usResponse: List<NetworkNewsArticle> = emptyList()
        viewModelScope.launch {
            val indiaHeadLinesDeferred = async { newsRepository.fetchTopHeadlines("in") }
            val usHeadLinesDeferred = async { newsRepository.fetchTopHeadlines("us") }

           when(val indiaHeadlines = indiaHeadLinesDeferred.await()){
               is NetworkResult.Success -> {
                   indiaResponse = indiaHeadlines.data.articles
                   var indiaNewsResponseEntity : List<NewsArticleEntity> = emptyList()
                   indiaNewsResponseEntity = indiaHeadlines.data.articles.map {
                       it.toNewsArticleEntity("india")
                   }
                   Log.e("IndiaNewsResponseEntity", indiaNewsResponseEntity.toString())
               }
               is NetworkResult.Error -> Log.e("ResponseIndia", "${indiaHeadlines.statusCode} & ${indiaHeadlines.message}")
               is NetworkResult.Exception -> Log.e("ResponseIndia", "${indiaHeadlines.e}")
           }
            when(val usHeadlines = usHeadLinesDeferred.await()){
               is NetworkResult.Success -> {
                   usResponse = usHeadlines.data.articles
               }
               is NetworkResult.Error -> Log.e("ResponseUs", "${usHeadlines.statusCode} & ${usHeadlines.message}")
               is NetworkResult.Exception -> Log.e("ResponseUs", "${usHeadlines.e}")
            }
            val newsFeed = NewsFeed(
                highlightedNews = indiaResponse[Random.nextInt(0, indiaResponse.size)].toUiArticleResponse(),
                recommendedNews = indiaResponse.map { it.toUiArticleResponse() },
                popularNews = usResponse.map { it.toUiArticleResponse() },
                recentNews = usResponse.map { it.toUiArticleResponse() }
            )
            //Needs changes in implementation. After MVP

           viewModelState.update {
               it.copy(
                   newsFeed = newsFeed,
                   isLoading = false,
                   isArticleOpen = false
               )
           }
       }

    }

    companion object{
        /*
        fun provideFactory(
            newsRepository: NewsRepository,
            preSelectedPostId: String? = null
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(newsRepository ,preSelectedPostId) as T
            }
        }
         */
    }

}