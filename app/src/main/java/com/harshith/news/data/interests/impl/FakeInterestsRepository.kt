package com.harshith.news.data.interests.impl

import com.harshith.news.data.Result
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.InterestsSection
import com.harshith.news.data.interests.TopicSelection
import kotlinx.coroutines.flow.Flow

class FakeInterestsRepository: InterestsRepository {
    override suspend fun getTopics(): Result<List<InterestsSection>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPeople(): Result<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPublications(): Result<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleTopicSelection(topic: TopicSelection) {
        TODO("Not yet implemented")
    }

    override suspend fun togglePersonSelection(person: String) {
        TODO("Not yet implemented")
    }

    override suspend fun togglePublicationSelection(publication: String) {
        TODO("Not yet implemented")
    }

    override fun observerTopicsSelected(): Flow<Set<TopicSelection>> {
        TODO("Not yet implemented")
    }

    override fun observePeopleSelected(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override fun observePublicationSelected(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }
}