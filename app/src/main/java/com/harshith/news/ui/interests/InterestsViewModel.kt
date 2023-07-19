package com.harshith.news.ui.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.InterestsSection
import com.harshith.news.data.interests.TopicSelection
import com.harshith.news.data.successOr
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class InterestUiState(
    val topics: List<InterestsSection> = emptyList(),
    val people: List<String> = emptyList(),
    val publications: List<String> = emptyList(),
    val loading: Boolean = false
)
class InterestsViewModel(
    private val interestsRepository: InterestsRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(InterestUiState(loading = true))
    val uiState: StateFlow<InterestUiState> = _uiState.asStateFlow()

    val selectedTopics = interestsRepository.observerTopicsSelected().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    val selectedPeople = interestsRepository.observePeopleSelected().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    val selectedPublication = interestsRepository.observePublicationSelected().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptySet()
    )

    init {
        refreshPosts()
    }

    fun toggleTopicSelection(topic: TopicSelection){
        viewModelScope.launch {
            interestsRepository.toggleTopicSelection(topic)
        }
    }

    fun togglePeopleSelection(person: String){
        viewModelScope.launch {
            interestsRepository.togglePersonSelection(person)
        }
    }

    fun togglePublicationSelection(publication: String){
        viewModelScope.launch {
            interestsRepository.togglePublicationSelection(publication)
        }
    }

    private fun refreshPosts(){
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            val topicsDeferred = async { interestsRepository.getTopics() }
            val peopleDeferred = async{ interestsRepository.getPeople() }
            val publicationsDeferred = async { interestsRepository.getPublications() }

            val topics = topicsDeferred.await().successOr(emptyList())
            val people = peopleDeferred.await().successOr(emptyList())
            val publications = publicationsDeferred.await().successOr(emptyList())

            _uiState.update {
                it.copy(
                    topics = topics,
                    people = people,
                    publications = publications,
                    loading = false
                )
            }
        }
    }
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