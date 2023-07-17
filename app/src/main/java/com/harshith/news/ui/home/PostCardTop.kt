package com.harshith.news.ui.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.posts.post1
import com.harshith.news.data.posts.posts
import com.harshith.news.model.Post
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.util.CompletePreviews

@Composable
fun PostCardTop(
    post: Post,
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

        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = post.title,
            style = typography.titleLarge,
            modifier = modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
        )

        Text(
            text = post.metadata.author.name,
            style = typography.labelLarge,
            modifier = modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)
        )
        Text(
            text = stringResource(
                id = R.string.post_min_read,
                formatArgs = arrayOf(
                    post.metadata.date,
                    post.metadata.readTimeMinutes
                )
            ),
            style = typography.bodySmall
        )
    }

}

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