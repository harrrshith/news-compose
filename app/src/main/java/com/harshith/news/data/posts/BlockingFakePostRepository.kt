package com.harshith.news.data.posts

import com.harshith.news.data.Result
import com.harshith.news.data.posts.impl.FakePostsRepository
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import com.harshith.news.util.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class BlockingFakePostRepository: PostRepository {
    private val favourites = MutableStateFlow<Set<String>>(setOf())
    private val postsFeed = MutableStateFlow<PostsFeed?>(null)
    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO){
            val post = posts.allPosts.find { it.id == postId }
            if(post == null){
                Result.Error(IllegalArgumentException("Unable to find post"))
            }else{
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        postsFeed.update { posts }
        return Result.Success(posts)
    }

    override fun observeFavourites(): Flow<Set<String>> = favourites

    override fun observePostFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavourite(postId: String?) {
        favourites.update { it.addOrRemove(postId!!) }
    }

}