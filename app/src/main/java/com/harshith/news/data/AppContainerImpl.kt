package com.harshith.news.data

import android.content.Context
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.impl.FakeInterestsRepository
import com.harshith.news.data.repository.NewsRepository
import com.harshith.news.data.repository.NewsRepositoryImpl
import com.harshith.news.data.posts.PostRepository
import com.harshith.news.data.posts.impl.FakePostsRepository


interface AppContainer{
    val interestsRepository: InterestsRepository
}
class AppContainerImpl: AppContainer {
    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }

}