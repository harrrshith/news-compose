package com.harshith.news.data.interests.impl

import com.harshith.news.data.Result
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.InterestsSection
import com.harshith.news.data.interests.TopicSelection
import com.harshith.news.util.addOrRemove
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update

class FakeInterestsRepository: InterestsRepository {

    private val topics by lazy {
        listOf(
            InterestsSection("Android", listOf("Jetpack Compose", "Kotlin", "Jetpack")),
            InterestsSection(
                "Programming",
                listOf("Kotlin", "Declarative UIs", "Java", "Unidirectional Data Flow", "C++")
            ),
            InterestsSection("Technology", listOf("Pixel", "Google"))
        )
    }

    private val people by lazy {
        listOf(
            "Kobalt Toral",
            "K'Kola Uvarek",
            "Kris Vriloc",
            "Grala Valdyr",
            "Kruel Valaxar",
            "L'Elij Venonn",
            "Kraag Solazarn",
            "Tava Targesh",
            "Kemarrin Muuda"
        )
    }

    private val publications by lazy {
        listOf(
            "Kotlin Vibe",
            "Compose Mix",
            "Compose Breakdown",
            "Android Pursue",
            "Kotlin Watchman",
            "Jetpack Ark",
            "Composeshack",
            "Jetpack Point",
            "Compose Tribune"
        )
    }

    private val selectedTopic = MutableStateFlow(setOf<TopicSelection>())
    private val selectedPeople = MutableStateFlow(setOf<String>())
    private val selectedPublication = MutableStateFlow(setOf<String>())
    override suspend fun getTopics(): Result<List<InterestsSection>> {
        return Result.Success(topics)
    }

    override suspend fun getPeople(): Result<List<String>> {
        return Result.Success(people)
    }

    override suspend fun getPublications(): Result<List<String>> {
        return Result.Success(publications)
    }

    override suspend fun toggleTopicSelection(topic: TopicSelection) {
        selectedTopic.update {
            it.addOrRemove(topic)
        }
    }

    override suspend fun togglePersonSelection(person: String) {
        selectedPeople.update {
            it.addOrRemove(person)
        }
    }

    override suspend fun togglePublicationSelection(publication: String) {
        selectedPublication.update {
            it.addOrRemove(publication)
        }
    }

    override fun observerTopicsSelected(): Flow<Set<TopicSelection>> = selectedTopic

    override fun observePeopleSelected(): Flow<Set<String>> = selectedPeople

    override fun observePublicationSelected(): Flow<Set<String>> = selectedPublication
}