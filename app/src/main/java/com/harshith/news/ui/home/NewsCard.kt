package com.harshith.news.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticleClass
import com.harshith.news.ui.theme.NewsTheme

@Composable
fun NewsCard(
    newsArticle: NewsArticle
){
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(.8f)
            .padding(10.dp, 8.dp)
    ) {
        Box(modifier = Modifier.wrapContentSize()){
            Image(
                painter = painterResource(id = R.drawable.image_post),
                modifier = Modifier.background(Color.Black).alpha(.6f).
                fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                val textColor = Color.White
                PublisherAndTime(newsArticle.sourceId, newsArticle.pubDate)
                Text(
                    text = newsArticle.title ?: "No title",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = textColor
                )
            }

        }
    }

}

@Composable
fun PublisherAndTime(creator: String?, publishDate: String?){
    return Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val textStyle = MaterialTheme.typography.labelSmall
        val textColor = Color.White
        Text(text = creator?: "", style = textStyle, color = textColor)
        Text(text = publishDate?: "", style = textStyle, color = textColor)
    }
}

@Preview
@Composable
fun PreviewNewsCard(){
    NewsTheme {
        Surface(Modifier.fillMaxSize()) {
            NewsCard(newsArticleClass)
        }
    }
}