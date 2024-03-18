package com.harshith.news.util

import com.harshith.news.data.local.entities.NewsArticleEntity
import com.harshith.news.data.network.model.NetworkNewsArticle
import com.harshith.news.data.network.model.NetworkNewsResponse
import com.harshith.news.model.NewsArticle

fun NetworkNewsArticle.toNewsArticle() = NewsArticle(
    content = this.content,
    creator = this.creator,
    description = this.description,
    imageUrl = this.imageUrl,
    link = this.link,
    pubDate = this.pubDate,
    title = this.title,
    sourceId = this.source.id?: "",
    sourceName = this.source.name?: ""
)

fun List<NetworkNewsArticle>.toNewArticleList() = map { networkNewsArticle ->
    NewsArticle(
        content = networkNewsArticle.content,
        creator = networkNewsArticle.creator,
        description = networkNewsArticle.description,
        imageUrl = networkNewsArticle.imageUrl?.toEncodedUrl(),
        link = networkNewsArticle.link?.toEncodedUrl(),
        pubDate = networkNewsArticle.pubDate,
        title = networkNewsArticle.title,
        sourceId =  networkNewsArticle.source.id,
        sourceName = networkNewsArticle.source.name
    )
}

fun NewsArticle.toNewsArticleEntity() = NewsArticleEntity(
    content = this.content,
    creator = this.creator,
    description = this.description,
    imageUrl = this.imageUrl,
    link = this.link,
    pubDate = this.pubDate,
    title = this.title,
    sourceId = this.sourceId,
    sourceName = this.sourceName
)

fun List<NewsArticleEntity>.toNewsArticle() = map{ newsArticleEntity ->
    NewsArticle(
        content = newsArticleEntity.content,
        creator = newsArticleEntity.creator,
        description = newsArticleEntity.description,
        imageUrl = newsArticleEntity.imageUrl?.toEncodedUrl(),
        link = newsArticleEntity.link?.toEncodedUrl(),
        pubDate = newsArticleEntity.pubDate,
        title = newsArticleEntity.title,
        sourceId = newsArticleEntity.sourceId,
        sourceName = newsArticleEntity.sourceName
    )

}