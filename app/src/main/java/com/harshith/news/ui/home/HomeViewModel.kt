package com.harshith.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.network.repository.NewsRepository
import com.harshith.news.model.news.Article
import com.harshith.news.util.ErrorMessage
import com.harshith.news.util.toNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
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
        viewModelScope.launch {
            getIndiaNews()
        }
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
    private suspend fun getIndiaNews(){
        var indiaResponse: List<Article> = emptyList()
        var usResponse: List<Article> = emptyList()

        newsRepository.fetchTopHeadlines("in").collect{newsEntity ->
            Log.e("ResponseNewsEntity", "$newsEntity")
            indiaResponse = newsEntity.map { it.toNewsArticle() }
        }
        newsRepository.indiaNewsResponse.collect{newsEntity ->
            Log.e("ResponseNew", "$newsEntity")
            indiaResponse = newsEntity.map { it.toNewsArticle() }
        }

        newsRepository.fetchTopHeadlines("us").collect{newsEntity ->
            Log.e("ResponseNewsEntity", "$newsEntity")
            usResponse = newsEntity.map { it.toNewsArticle() }
        }

        val newsFeed = NewsFeed(
            highlightedNews = indiaResponse[Random.nextInt(0, indiaResponse.size)],
            recommendedNews = indiaResponse,
            popularNews = usResponse,
            recentNews = usResponse
        )

        viewModelState.update {
            it.copy(
                newsFeed = newsFeed,
                isLoading = false,
                isArticleOpen = false
            )
        }
    }
}