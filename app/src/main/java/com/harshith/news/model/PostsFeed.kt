package com.harshith.news.model

data class PostsFeed(
    val highLightedPost: Post,
    val recommendedPosts: List<Post>,
    val popularPosts: List<Post>,
    val recentPosts: List<Post>
){
    val allPosts: List<Post> = listOf(highLightedPost) + recommendedPosts + popularPosts + recentPosts
}