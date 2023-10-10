package com.harshith.news.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.data.previewData.article
import com.harshith.news.model.news.Article
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.util.Constants
import com.harshith.news.util.parseTime

private val defaultSpacerSize = 16.dp
@Composable
fun PostContent(
    article: Article,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
){
    LazyColumn(
        contentPadding = PaddingValues(defaultSpacerSize),
        modifier = modifier,
        state = state
    ){
        postContentItems(article = article)
    }
}

fun LazyListScope.postContentItems(article: Article){
    item {
        PostHeaderImage(article = article)
        Spacer(modifier = Modifier.height(defaultSpacerSize))
    }
    item {
        Text(
            text = article.title ?: "",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer (modifier = Modifier.height(defaultSpacerSize))
    }
    item {
        Text(
            text = article.content ?: "",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(defaultSpacerSize))
    }
    item {
        PostMetaData(
            authorName = article.author ?: "",
            publishedData = article.publishedAt  ?: "",
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Spacer(modifier = Modifier.height(defaultSpacerSize))
    }
}

@Composable
fun PostHeaderImage(article: Article){
    val modifier = Modifier
        .heightIn(min = 180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)

    AsyncImage(
        model = article.urlToImage ?: Constants.PLACEHOLDER_IMAGE,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostMetaData(
    authorName: String,
    publishedData: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {}
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = authorName,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.post_min_read,
                    formatArgs = arrayOf(
                        parseTime(publishedData),
                        "8"
                    )
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostContent(){
    NewsTheme {
        Surface {
            PostContent(
                article = article
            )
        }
    }
}