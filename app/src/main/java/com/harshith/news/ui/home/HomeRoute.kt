package com.harshith.news.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.logE
import com.harshith.news.util.logV

const val TAG = "HOME_ROUTE"
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    navigateToArticleDetail: (String) -> Unit,
    snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    },
){
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
//    HomeRoute(
//        isExpandedScreen = isExpandedScreen,
//        onToggleFavourite = { homeViewModel.toggleFavourites(it) },
//        onSelectPosts = { navigateToArticleDetail(it) },
//        onSelectPostsInLargeScreen = { homeViewModel.selectArticle(it) },
//        onInteractWithFeed = { homeViewModel.interactWithFeed() },
//        onInteractWidthArticleDetails = { homeViewModel.interactWithArticleDetails(it) },
//        onSearchInputChange = { homeViewModel.onSearchInputChanged(it) },
//        openDrawer = { openDrawer() },
//        snackbarHostState = snackbarHostState
//    )
    HomeFeed(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState
    )

}

@Composable
private fun HomeFeed(
    modifier: Modifier,
    uiState: HomeUiState,

){
    val newsFeed  = when(uiState){
        is HomeUiState.HasNews -> uiState.newsFeed
        is HomeUiState.NoNews -> uiState.newsFeed
    }

    newsFeed?.let {newsArticle ->
        LazyColumn(modifier){
            items(newsArticle.homeFeedNews?.size!!){
                Text(text = newsArticle.homeFeedNews[it].title ?: "No Title", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    } ?: Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "No News Found")
    }
}

@Composable
fun HomeRoute(
    isExpandedScreen: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onSelectPostsInLargeScreen: (String) -> Unit,
    onInteractWithFeed: () -> Unit,
    onInteractWidthArticleDetails: (String) -> Unit,
    onSearchInputChange: (String) -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState
){
    val homeLazyListState = rememberLazyListState()
//    HomeFeedWithArticleDetailsScreen(
//        uiState = uiState,
//        showTopAppBar = !isExpandedScreen,
//        onToggleFavourite = onToggleFavourite,
//        onSelectPosts = onSelectPostsInLargeScreen,
//        onRefreshPosts = { onRefreshPosts() },
//        onErrorDismiss = onErrorDismiss,
//        onInteractWithList = { onInteractWithFeed() },
//        onInteractWithDetail = onInteractWidthArticleDetails,
//        openDrawer = { openDrawer() },
//        homeListLazyListState = homeLazyListState,
//        articleDetailsLazyListStates = articleDetailsLazyListState!!,
//        snackbarHostState = snackbarHostState,
//        onSearchInputChanged = onSearchInputChange
//    )
    TAG.logE("Hello")
    HomeFeedScreen(
        showTopAppBar = !isExpandedScreen,
        onToggleFavourite = onToggleFavourite,
        onSelectPosts = { onSelectPosts(it)},
        openDrawer = { openDrawer() },
        homeListLazyListState = homeLazyListState,
        snackbarHostState = snackbarHostState,
    )
}





