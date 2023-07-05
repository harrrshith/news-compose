package com.harshith.news.data.posts.impl

import com.harshith.news.data.posts.PostRepository
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.harshith.news.data.Result
import com.harshith.news.data.posts.posts
import com.harshith.news.util.addOrRemove
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakePostsRepository: PostRepository {
    private val favourite = MutableStateFlow<Set<String>>(setOf())
    private val postsFeed = MutableStateFlow<PostsFeed?>(null)
    override suspend fun getPost(postId: String?): Result<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO){
            delay(800)
            if(shouldRandomlyFail()){
                Result.Error(IllegalStateException())
            }else{
                postsFeed.update { posts }
                Result.Success(posts)
            }
        }
    }

    override fun observeFavourites(): Flow<Set<String>> = favourite

    override fun observePostFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavourite(postId: String?) {
        favourite.update {
            it.addOrRemove(postId!!)
        }
    }

    private var requestCount = 0
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}