package com.harshith.news.ui.article

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.Result
import com.harshith.news.data.posts.BlockingFakePostRepository
import com.harshith.news.data.posts.post1
import com.harshith.news.model.Post
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.BookMarkButton
import com.harshith.news.ui.utils.FavouriteButton
import com.harshith.news.ui.utils.ShareButton
import com.harshith.news.ui.utils.TextSettingsButton
import kotlinx.coroutines.runBlocking

@Composable
fun ArticleScreen(
    text: String,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState()
){
    var showUnimplementedActionDialog by rememberSaveable{ mutableStateOf(false)}
    if(showUnimplementedActionDialog){
        FunctionalityNotAvailablePopup{showUnimplementedActionDialog = false}
    }
    /**
    Row(modifier.fillMaxSize()) {
        val context = LocalContext.current
        ArticleScreenContent(
            article = article,
            navigationIconContent = {
                if(!isExpandedScreen){
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            bottomBarContent = {
                if(!isExpandedScreen){
                    BottomAppBar(
                        actions = {
                            FavouriteButton(onClick = {showUnimplementedActionDialog = true})
                            BookMarkButton(isBookmarked = isFavourite, onClick = onToggleFavourite)
                            ShareButton(onClick = { sharePost(article, context) })
                            TextSettingsButton(onClick = {showUnimplementedActionDialog = true})
                        }
                    )
                }
            },
            lazyListState = lazyListState
        )
    }
     */
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreenContent(
    article: Article,
    navigationIconContent: @Composable () -> Unit = {},
    bottomBarContent: @Composable () -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState()
){
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)

    Scaffold(
        topBar = {
            TopAppBar(
                title = article.title ?: "",
                navigationIconContent = navigationIconContent,
                scrollBehaviour = scrollBehavior
            )
        },
        bottomBar = bottomBarContent,
    ){innerPadding ->
        PostContent(
            article = article,
            modifier = Modifier
                .padding(innerPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            state = lazyListState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIconContent: @Composable ()-> Unit = {},
    scrollBehaviour: TopAppBarScrollBehavior?
){
    CenterAlignedTopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_android),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                )
                Text(
                    text = stringResource(id = R.string.published_in, title),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        navigationIcon = navigationIconContent,
        scrollBehavior = scrollBehaviour,
        modifier = modifier
    )
}

@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = { onDismiss() },
        text = {
            Text(
                text = stringResource(id = R.string.not_implemented),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}
fun sharePost(article: Article, context: Context){

}

@Preview("Article Screen")
@Composable
fun PreviewArticleScreen(){
    NewsTheme {
        Surface {
            ArticleScreen(
                text = "Hello",
                isExpandedScreen = false,
                onBack = { },
                isFavourite = true,
                onToggleFavourite = { }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewTopAppBar(){
    NewsTheme {
        TopAppBar(
            title = "Android Developers",
            scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior())
    }
}

/**
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
 */