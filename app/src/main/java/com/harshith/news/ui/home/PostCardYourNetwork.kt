package com.harshith.news.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.data.posts.post1
import com.harshith.news.model.Post
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source
import com.harshith.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCardPopular(
    article: Article,
    navigateToArticle: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        onClick = {  },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(280.dp, 240.dp)
    ) {
        Column {
            AsyncImage(
                model = article.urlToImage ?: R.drawable.image_post_thumb,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = article.author ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(

                    text = stringResource(
                        id = R.string.post_min_read,
                        formatArgs = arrayOf(
                            article.publishedAt ?: "",
                            "5"
                        )
                    ),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    NewsTheme {
        Surface {
            PostCardPopular(
                article = Article(
                    "",
                    "Author",
                    "content",
                    "description",
                    "publishedAt",
                    Source(
                        "",
                        ""
                    ),
                    stringResource(id = R.string.lorem_title),
                    "url",
                    ""
                ),
                navigateToArticle = {}
            )
        }
    }
}
