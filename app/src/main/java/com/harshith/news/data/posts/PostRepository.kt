package com.harshith.news.data.posts

import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost(postId: String?): Result<Post>
    suspend fun getPostsFeed(): Result<PostsFeed>
    fun observeFavourites(): Flow<Set<String>>
    fun observePostFeed(): Flow<PostsFeed?>
    suspend fun toggleFavourite(postId: String?)
}