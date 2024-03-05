package com.harshith.news.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Size
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticle
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.getFormattedTimeStamp
import com.harshith.news.util.toDecodedUrl

@Composable
fun NewsCardHorizontal(
    newsArticle: NewsArticle,
    onSelectPosts: (NewsArticle) -> Unit,
    width: Dp
){
    val textColor = Color.White
    val cardColor = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    )
    Card(
        modifier = Modifier.clickable(
            onClick = { onSelectPosts(newsArticle) }
        )
            .wrapContentSize()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .widthIn(350.dp, width-20.dp)
                .aspectRatio(18f / 9f)
        ){
            GradientImage(newsArticle.imageUrl, Modifier.aspectRatio(18f / 9f))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ){
                newsArticle.sourceName?.let {
                    Card(
                        colors = cardColor,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clip(CircleShape)
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
                .aspectRatio(10f / 7f)
                .clip(MaterialTheme.shapes.large)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            newsArticle.creator?.let {
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
        model = imageUrl?.toDecodedUrl(),
        contentDescription = "image",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .drawWithCache {
                val gradient = Brush.linearGradient(
                    colors = listOf(Color.Black, Color.Transparent),
                    start = Offset(-100f, 600f),
                    end = Offset(-80f, 0f),
                    tileMode = TileMode.Clamp
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
        publishDate?.let {
            Text(
                text = getFormattedTimeStamp(it),
                style = MaterialTheme.typography.labelSmall,
                color = textColor,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        creator?.let {
            Text(
                text = it.uppercase(),
                style = MaterialTheme.typography.headlineSmall,
                color = textColor,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
        }
    }
}

@Preview(apiLevel = 33)
@Composable
fun PreviewNewsCard(){
    NewsTheme {
        Surface(Modifier.fillMaxSize()) {
            Column {
                NewsCardHorizontal(newsArticle , {}, 380.dp)
                NewsCardVertical(newsArticle)
            }
        }
    }
}
