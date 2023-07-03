package com.harshith.news.data

import android.content.Context
import com.harshith.news.data.interests.InterestsRepository
import com.harshith.news.data.interests.impl.FakeInterestsRepository
import com.harshith.news.data.posts.PostRepository
import com.harshith.news.data.posts.impl.FakePostsRepository


interface AppContainer{
    val postRepository: PostRepository
    val interestsRepository: InterestsRepository
}
class AppContainerImpl(private val applicationContext: Context): AppContainer {
    override val postRepository: PostRepository by lazy {
        FakePostsRepository()
    }
    override val interestsRepository: InterestsRepository by lazy {
        FakeInterestsRepository()
    }

}