package com.harshith.news.util

import com.harshith.news.data.network.model.NetworkNewsArticle
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle

fun NetworkNewsArticle.toNewsArticle() = NewsArticle(
    articleId = this.articleId,
    category = this.category,
    content = this.content,
    country = this.country,
    creator = this.creator,
    description = this.description,
    imageUrl = this.imageUrl,
    keywords = this.keywords,
    language = this.language,
    link = this.link,
    pubDate = this.pubDate,
    sourceId = this.sourceId,
    sourcePriority = this.sourcePriority,
    title = this.title,
    videoUrl = this.videoUrl
)

fun List<NetworkNewsArticle>.toNewArticleList() = map { networkNewsArticle ->
    NewsArticle(
        articleId = networkNewsArticle.articleId,
        category = networkNewsArticle.category,
        content = networkNewsArticle.content,
        country = networkNewsArticle.country,
        creator = networkNewsArticle.creator,
        description = networkNewsArticle.description,
        imageUrl = networkNewsArticle.imageUrl,
        keywords = networkNewsArticle.keywords,
        language = networkNewsArticle.language,
        link = networkNewsArticle.link,
        pubDate = networkNewsArticle.pubDate,
        sourceId = networkNewsArticle.sourceId,
        sourcePriority = networkNewsArticle.sourcePriority,
        title = networkNewsArticle.title,
        videoUrl = networkNewsArticle.videoUrl
    )
}