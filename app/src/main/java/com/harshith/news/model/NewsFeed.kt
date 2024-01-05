package com.harshith.news.model

import com.harshith.news.model.NewsArticle

data class NewsFeed(
    val homeFeedNews: List<NewsArticle>? = null,
    val indiaSportsNews: List<NewsArticle>? = null,
    val indiaTechNews: List<NewsArticle>? = null,
    val indiaPoliticsNews: List<NewsArticle>? = null,
    //THe plan is to add more and more content to newsFeed
)
