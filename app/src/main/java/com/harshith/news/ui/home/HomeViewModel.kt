package com.harshith.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.harshith.news.R
import com.harshith.news.data.Result
import com.harshith.news.data.network.RetrofitClientInstance
import com.harshith.news.data.posts.PostRepository
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import com.harshith.news.util.ErrorMessage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID

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
        val postsFeed: PostsFeed,
        val selectedPost: Post,
        val isArticleOpen: Boolean,
        val favourites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        override val searchInput: String
    ): HomeUiState

}

private data class HomeViewModelState(
    val postsFeed: PostsFeed? = null,
    val selectedPostId: String? = null,
    val isArticleOpen: Boolean = false,
    val favourites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: List<ErrorMessage> = emptyList(),
    val searchInput: String = ""
){
    fun toUiState(): HomeUiState =
        if(postsFeed == null){
            HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessage = errorMessage,
                searchInput = searchInput
            )
        }else{
            HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts.find {
                    it.id == selectedPostId
                } ?: postsFeed.highlightedPost,
                isArticleOpen = isArticleOpen,
                favourites = favourites,
                isLoading = isLoading,
                errorMessage = errorMessage,
                searchInput = searchInput
            )
        }
}
class HomeViewModel(
    private val postRepository: PostRepository,
    preSelectedPostId: String?
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
            selectedPostId = preSelectedPostId,
            isArticleOpen = preSelectedPostId != null
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
        refreshPosts()
        viewModelScope.launch {
            postRepository.observeFavourites().collect{_favourites ->
                viewModelState.update { it.copy(favourites = _favourites) }
            }
        }

    }

    fun refreshPosts(){
        getNewsResponse()
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = postRepository.getPostsFeed()
            viewModelState.update {
                when(result){
                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
                    is Result.Error -> {
                        val errorMessage = it.errorMessage + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.load_error
                        )
                        it.copy(
                            errorMessage = errorMessage,
                            isLoading = false
                        )
                    }

                }
            }

        }
    }

    fun toggleFavourites(_postId: String?){
        viewModelScope.launch {
            postRepository.toggleFavourite(_postId)
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

    fun getNewsResponse() = runBlocking{
        val service = RetrofitClientInstance.createInstance()
        coroutineScope {
            launch {
                val result = service.getTopHeadlines("in").body()
                Log.e("response", "${result?.articles?.get(0)}")
            }
            launch {
                val result = service.getTopHeadlines("in").body()
                Log.e("response", "${result?.articles?.get(0)}")
            }
        }
    }

    companion object{
        fun provideFactory(
            postRepository: PostRepository,
            preSelectedPostId: String? = null
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(postRepository, preSelectedPostId) as T
            }
        }
    }

}