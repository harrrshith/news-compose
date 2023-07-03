package com.harshith.news.model

import androidx.annotation.DrawableRes

data class Post(
    val id: String,
    val title: String,
    val subtitle: String,
    val url: String,
    val publication: Publication? = null,
    val metadata: MetaData,
    val paragraph: List<Paragraph>,
    @DrawableRes val imageId: Int,
    @DrawableRes val imageResId: Int
)

data class Publication(
    val name: String,
    val logUrl: String
)

data class MetaData(
    val author: PostAuthor,
    val data: String,
    val readTimeMinutes: String
)

data class PostAuthor(
    val name: String,
    val url: String? = null
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

enum class Markup{
    Link,
    Code,
    Italic,
    Bold
}

enum class ParagraphType{
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet
}