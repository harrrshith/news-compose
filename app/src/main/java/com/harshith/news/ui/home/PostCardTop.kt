package com.harshith.news.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.model.news.Article
import com.harshith.news.util.parseTime

@Composable
fun PostCardTop(
    article: Article,
    modifier: Modifier = Modifier
){
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = modifier
            .heightIn(180.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)

        AsyncImage(
            model = article.urlToImage ?: R.drawable.image_post,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.title ?: "",
            style = typography.titleLarge,
            modifier = modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
        )
        Log.e("Response", "${article.author}")

        Text(
            text = article.author ?: "",
            style = typography.labelLarge,
            modifier = modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)
        )
        Text(
            text = stringResource(
                id = R.string.post_min_read,
                formatArgs = arrayOf(
                    parseTime(article.publishedAt!!),
                    "5"
                )
            ),
            style = typography.bodySmall
        )
    }

}
/*
@CompletePreviews
@Composable
fun PreviewPostCardTop(){
    NewsTheme {
        Surface {
            PostCardTop(
                post = posts.highlightedPost
            )
        }
    }
}
 */