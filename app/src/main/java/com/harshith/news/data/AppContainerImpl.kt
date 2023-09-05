package com.harshith.news.data

import android.content.Context
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.impl.FakeInterestsRepository
import com.harshith.news.data.network.repository.NewsRepository
import com.harshith.news.data.network.repository.NewsRepositoryImpl
import com.harshith.news.data.posts.PostRepository
import com.harshith.news.data.posts.impl.FakePostsRepository


interface AppContainer{
    val postRepository: PostRepository
    val interestsRepository: InterestsRepository
    val newsRepository: NewsRepository
}
class AppContainerImpl: AppContainer {
    override val postRepository: PostRepository by lazy {
        FakePostsRepository()
    }
    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }
    override val newsRepository: NewsRepository by lazy{
        NewsRepositoryImpl()
    }

}