package com.harshith.news.model

import com.harshith.news.model.NewsArticle

data class NewsFeed(
    val homeFeedNews: List<NewsArticle>? = emptyList(),
    val indiaSportsNews: List<NewsArticle>? = emptyList(),
    val indiaTechNews: List<NewsArticle>? = emptyList(),
    val indiaPoliticsNews: List<NewsArticle>? = emptyList(),
    //THe plan is to add more and more content to newsFeed
)
