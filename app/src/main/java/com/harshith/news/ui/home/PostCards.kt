package com.harshith.news.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.BookMarkButton

@Composable
fun PostCardSimple(
    article: Article,
    navigateToArticle: (String) -> Unit,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit
){
    val bookMarkAction = stringResource(if(isFavourite) R.string.unbookmark else R.string.bookmark)
    val configuration = LocalConfiguration.current
    Row(
        modifier = Modifier
            .widthIn(min = 200.dp, max = configuration.screenWidthDp.dp)
            .clickable(onClick = { })
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = bookMarkAction,
                        action = { onToggleFavourite(); true }
                    )
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        PostImage(
            article = article,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            PostTitle(article)
            //AuthorReadTime(article)
        }
        BookMarkButton(
            isBookmarked = isFavourite,
            onClick = { onToggleFavourite() },
            modifier = Modifier
                .clearAndSetSemantics { }
                .padding(vertical = 2.dp, horizontal = 6.dp)
        )
    }
}

@Composable
fun PostImage(
    article: Article,
    modifier: Modifier = Modifier
){
    AsyncImage(
        model = article.urlToImage ?: R.drawable.image_post,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(80.dp, 60.dp)
            .clip(MaterialTheme.shapes.small),
    )
}
@Composable
fun PostTitle(
    article: Article,
){
    Text(
        text = article.title ?: "",
        style = MaterialTheme.typography.titleSmall,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun AuthorReadTime(
    article: Article,
    modifier: Modifier = Modifier
){
    Row(modifier) {
        Text(
            text = stringResource(
                id = R.string.post_min_read,
                formatArgs = arrayOf(
                    article.author ?: "",
                    article.publishedAt ?: ""
                )
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun PostCardHistory(
    article: Article,
    navigateToArticle: (String) -> Unit
){
    var openDialog by remember {
        mutableStateOf(false)
    }
    val configuration = LocalConfiguration.current
    Row(
        modifier = Modifier
            .widthIn(min = 200.dp, max = configuration.screenWidthDp.dp)
            .clickable(onClick = { }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PostImage(
            article = article,
            modifier = Modifier.padding(8.dp, 4.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            PostTitle(article = article)
        }
        IconButton(
            onClick = { openDialog = true }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null
            )
        }
    }
    if(openDialog){
        AlertDialog(
            modifier = Modifier.padding(20.dp),
            onDismissRequest = { openDialog = false },
            title = {
                Text(
                    text = stringResource(id = R.string.fewer_stories),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.fewer_stories),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.agree),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { openDialog = false }
                )
            }
        )
    }
}

@Preview
@Composable
fun PreviewPostCard(){
    NewsTheme {
        Surface {
            PostCardSimple(
                Article(
                    "",
                    "Harshith",
                    "",
                    "description",
                    "publishedAt",
                    Source(
                        "",
                        ""
                    ),
                    "title",
                    "url",
                    "urlToImage"
                ),
                {},
                false,
                {}
            )
        }
    }
}
@Preview
@Composable
fun PreviewPostCardHistory(){
    NewsTheme {
        Surface {
            PostCardHistory(
                article = Article(
                    "",
                    "Harshith",
                    "",
                    "description",
                    "publishedAt",
                    Source(
                        "",
                        ""
                    ),
                    "title",
                    "url",
                    "urlToImage"
                ),
            ){}
        }
    }
}
