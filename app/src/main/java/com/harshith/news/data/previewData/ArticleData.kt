package com.harshith.news.data.previewData

import com.harshith.news.R
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source

val article: Article = Article(
    uuid = "123456789",
    author = "Harshith Kumar",
    content = "The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.",
    description = "The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.",
    publishedAt = "2023-09-04T05:37:36Z",
    source = Source(
        "1",
        "Human"
    ),
    title = "Navigating with Compose",
    url = "https://www.google.com",
    urlToImage = R.drawable.image_post.toString()
)