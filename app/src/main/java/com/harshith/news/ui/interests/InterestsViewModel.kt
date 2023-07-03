package com.harshith.news.ui.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshith.news.data.interests.InterestsRepository

class InterestsViewModel(
    private val interestsRepository: InterestsRepository
): ViewModel() {

    companion object{
        fun provideFactory(
            interestsRepository: InterestsRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return InterestsViewModel(interestsRepository) as T
            }
        }
    }
}