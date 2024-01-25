package com.harshith.news.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticleClass
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.getFormattedTimeStamp

@Composable
fun NewsCard(
    newsArticle: NewsArticle
){
    val textColor = Color.White
    val cardColor = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    )
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .heightIn(min = 140.dp, max = 190.dp)
                .fillMaxWidth()
        ){
            GradientImage(newsArticle.imageUrl, Modifier.fillMaxSize())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ){
                newsArticle.category?.get(0)?.let {
                    Card(
                        colors = cardColor,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clip(MaterialTheme.shapes.extraLarge)
                    ) {
                        Text(text = it, fontSize = 12.sp, modifier = Modifier.padding(8.dp))
                    }
                }
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {

                    PublisherAndTime(newsArticle.sourceId, newsArticle.pubDate)
                    newsArticle.title?.let {
                        Text(
                            text =  it,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = textColor
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun GradientImage(imageUrl: String?, modifier: Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "image",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Black, Color.Transparent),
                    startY = size.height,
                    endY = size.height / 3
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.Multiply)
                }
            }
    )
}


@Composable
fun PublisherAndTime(creator: String?, publishDate: String?){
    return Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val textColor = Color.White
        creator?.let {
            Text(
                text = it.uppercase(),
                style = MaterialTheme.typography.headlineSmall,
                color = textColor,
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        publishDate?.let {
            Text(
                text = getFormattedTimeStamp(it),
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }
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