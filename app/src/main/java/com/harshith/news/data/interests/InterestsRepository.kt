package com.harshith.news.data.interests

import com.harshith.news.data.Result
import kotlinx.coroutines.flow.Flow

data class InterestsSection(
    val title: String,
    val interests: List<String>
)


data class TopicSelection(
    val section: String,
    val topic: String
)
interface InterestsRepository {

    suspend fun getTopics(): Result<List<InterestsSection>>
    suspend fun getPeople(): Result<List<String>>
    suspend fun getPublications(): Result<List<String>>
    suspend fun toggleTopicSelection(topic: TopicSelection)
    suspend fun togglePersonSelection(person: String)
    suspend fun togglePublicationSelection(publication: String)

    fun observerTopicsSelected(): Flow<Set<TopicSelection>>
    fun observePeopleSelected(): Flow<Set<String>>
    fun observePublicationSelected(): Flow<Set<String>>
}