package com.harshith.news.ui.home
//Home screen will have a appbar which will be obviously material with all those animations.
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.util.logI

@Composable
fun HomeFeedWithArticleDetailsScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onInteractWithList: () -> Unit,
    onInteractWithDetail: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onSearchInputChanged: (String) -> Unit,
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello")
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(text = "Landscape")
    }
    
}

@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
){
    val lazyListState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val newsFeed = when(uiState){
        is HomeUiState.HasNews -> uiState.newsFeed?.homeFeedNews
        is HomeUiState.NoNews -> emptyList()
    }
    newsFeed?.let { newsArticles ->
        TopHeadlines(newsArticle = newsArticles, lazyListState = lazyListState, screenWidth = screenWidth)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopHeadlines(
    newsArticle: List<NewsArticle>,
    lazyListState: LazyListState,
    screenWidth: Dp
){
    LazyRow(
        modifier = Modifier.padding(top = 50.dp),
        contentPadding = PaddingValues(
            vertical = 18.dp
        ),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState)
    ){
        items(newsArticle.size){index ->
            NewsCard(newsArticle = newsArticle[index], screenWidth)
        }
    }
}
@Composable
private fun HomeScreenWithList(
    showTopAppBar: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text(text = "Hello World", style = MaterialTheme.typography.displayLarge)
            Text(text = "Hello World", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

