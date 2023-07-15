package com.harshith.news.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.posts.post1
import com.harshith.news.data.posts.post3
import com.harshith.news.model.Post
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.BookMarkButton

@Composable
fun PostCardSimple(
    post: Post,
    navigateToArticle: (String) -> Unit,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit
){
    val bookMarkAction = stringResource(if(isFavourite) R.string.unbookmark else R.string.bookmark)

    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToArticle(post.id) })
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
            post = post,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            PostTitle(post)
            AuthorReadTime(post)
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
    post: Post,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(post.imageThumbId),
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}
@Composable
fun PostTitle(
    post: Post,
){
    Text(
        text = post.title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun AuthorReadTime(
    post: Post,
    modifier: Modifier = Modifier
){
    Row(modifier) {
        Text(
            text = stringResource(
                id = R.string.post_min_read,
                formatArgs = arrayOf(
                    post.metadata.author.name,
                    post.metadata.readTimeMinutes
                )
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCardHistory(
    post: Post,
    navigateToArticle: (String) -> Unit
){
    var openDialog by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToArticle(post.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PostImage(
            post = post,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.based_on_your_history).uppercase(),
                style = MaterialTheme.typography.labelMedium
            )
            PostTitle(post = post)
            AuthorReadTime(
                post = post,
                modifier = Modifier.padding(top = 4.dp)
            )
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
                post3,
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
                post1,
                {}
            )
        }
    }
}