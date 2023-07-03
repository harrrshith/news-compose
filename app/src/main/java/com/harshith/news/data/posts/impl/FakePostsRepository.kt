package com.harshith.news.data.posts.impl

import com.harshith.news.data.posts.PostRepository
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import kotlinx.coroutines.flow.Flow

class FakePostsRepository: PostRepository {
    override suspend fun getPost(postId: String?): Result<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        TODO("Not yet implemented")
    }

    override fun observeFavourites(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override fun observePostFeed(): Flow<PostsFeed?> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavourite(postId: String?) {
        TODO("Not yet implemented")
    }
}