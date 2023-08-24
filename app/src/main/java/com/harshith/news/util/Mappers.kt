package com.harshith.news.util

import com.harshith.news.data.network.entities.NewsArticleNetworkEntity
import com.harshith.news.data.network.entities.NewsSourceNetworkEntity
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source

fun NewsSourceNetworkEntity.toUiSourceResponse(): Source{
    return Source(
        id = this.id,
        name = this.name
    )
}
fun NewsArticleNetworkEntity.toUiArticleResponse(): Article{
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = this.source?.toUiSourceResponse(),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}