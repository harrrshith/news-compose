package com.harshith.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshith.news.data.posts.PostRepository

class HomeViewModel(
    private val postRepository: PostRepository,
    preSelectedPostId: String?
) : ViewModel() {

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