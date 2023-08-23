package com.harshith.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.network.NetworkResult
import com.harshith.news.data.network.entities.NewsArticleNetworkEntity
import com.harshith.news.data.network.repository.NewsRepository
import com.harshith.news.model.PostsFeed
import com.harshith.news.util.ErrorMessage
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

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
    val highlightedNews: NewsArticleNetworkEntity,
    val recommendedNews: List<NewsArticleNetworkEntity>,
    val popularNews: List<NewsArticleNetworkEntity>,
    val recentNews: List<NewsArticleNetworkEntity>
)

class HomeViewModel(
    private val newsRepository: NewsRepository,
    preSelectedPostId: String?
) : ViewModel() {
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
        viewModelScope.launch {

            getIndiaNewsResponse()
            //getUSANewsResponse()
        }
        /**
         * viewModelScope.launch {
         *             getIndiaNewsResponse()
         *             getUSANewsResponse()
         *             postRepository.observeFavourites().collect{_favourites ->
         *                 viewModelState.update { it.copy(favourites = _favourites) }
         *             }
         *         }
         */
    }

    /**
     * fun refreshPosts(){
     *         viewModelState.update { it.copy(isLoading = true) }
     *         viewModelScope.launch {
     *             val result = postRepository.getPostsFeed()
     *             viewModelState.update {
     *                 when(result){
     *                     is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
     *                     is Result.Error -> {
     *                         val errorMessage = it.errorMessage + ErrorMessage(
     *                             id = UUID.randomUUID().mostSignificantBits,
     *                             messageId = R.string.load_error
     *                         )
     *                         it.copy(
     *                             errorMessage = errorMessage,
     *                             isLoading = false
     *                         )
     *                     }
     *
     *                 }
     *             }
     *
     *         }
     *     }
     */

    fun toggleFavourites(_postId: String?){
        viewModelScope.launch {
            newsRepository.toggleFavourite(_postId)
        }
    }

    fun selectArticle(_postId: String?){
        interactWithArticleDetails(_postId)
    }

    fun onSearchInputChanged(_searchInput: String){
        viewModelState.update {
            it.copy(searchInput = _searchInput)
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

    private suspend fun getIndiaNewsResponse(){
        when(val response = newsRepository.fetchTopHeadlines("in")){
            is NetworkResult.Success -> {
               //Log.e("Response", "${response.data}")
            }
            is NetworkResult.Error -> Log.e("Response", "${response.statusCode} & ${response.message}")
            is NetworkResult.Exception -> Log.e("Response", "${response.e}")
        }

    }
    private fun getSomeResponse(){
        var indiaResponse: List<NewsArticleNetworkEntity> = emptyList()
        var usResponse: List<NewsArticleNetworkEntity> = emptyList()
        viewModelScope.launch {
            val indiaHeadLinesDeferred = async { newsRepository.fetchTopHeadlines("in") }
            val usHeadLinesDeferred = async { newsRepository.fetchTopHeadlines("us") }

           when(val indiaHeadlines = indiaHeadLinesDeferred.await()){
               is NetworkResult.Success -> {
                   //Log.e("ResponseIndia", "${indiaHeadlines.data.articles}")
                   indiaResponse = indiaHeadlines.data.articles
               }
               is NetworkResult.Error -> Log.e("ResponseIndia", "${indiaHeadlines.statusCode} & ${indiaHeadlines.message}")
               is NetworkResult.Exception -> Log.e("ResponseIndia", "${indiaHeadlines.e}")
           }

           when(val usHeadlines = usHeadLinesDeferred.await()){
               is NetworkResult.Success -> {
                   //Log.e("ResponseUs", "${usHeadlines.data.articles}")
                   usResponse = usHeadlines.data.articles
               }
               is NetworkResult.Error -> Log.e("ResponseUs", "${usHeadlines.statusCode} & ${usHeadlines.message}")
               is NetworkResult.Exception -> Log.e("ResponseUs", "${usHeadlines.e}")
           }
            val newsFeed = NewsFeed(
                highlightedNews = indiaResponse[0],
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

    companion object{
        fun provideFactory(
            newsRepository: NewsRepository,
            preSelectedPostId: String? = null
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(newsRepository ,preSelectedPostId) as T
            }
        }
    }

}