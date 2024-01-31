package com.harshith.news.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticleClass
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.getFormattedTimeStamp

@Composable
fun NewsCardHorizontal(
    newsArticle: NewsArticle,
    screenWidth: Dp
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
                .widthIn(min = 300.dp, max = (screenWidth - 40.dp))
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
                            .alpha(.8f)
                    ) {
                        Text(
                            text = it.uppercase(),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
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
fun NewsCardVertical(
    newsArticle: NewsArticle
){
    Row(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        GradientImage(
            imageUrl = newsArticle.imageUrl,
            modifier = Modifier
                .height(100.dp)
                .width(120.dp)
                .clip(MaterialTheme.shapes.large)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            newsArticle.category?.get(0)?.let {
                Text(
                    text = it.uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            newsArticle.title?.let {
                Text(
                    text = it,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            PublisherAndTime(creator = newsArticle.sourceId, publishDate = newsArticle.pubDate, textColor = Color.Black)
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
fun PublisherAndTime(creator: String?, publishDate: String?, textColor: Color = Color.White){
    return Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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
            Column {
                NewsCardHorizontal(newsArticleClass, 380.dp)
                NewsCardVertical(newsArticleClass)
            }
        }
    }
}
