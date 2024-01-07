package com.harshith.news.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.logE

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
        newsFeed = when(uiState){
            is HomeUiState.HasNews -> (uiState as HomeUiState.HasNews).newsFeed?.homeFeedNews!!
            is HomeUiState.NoNews -> emptyList()
        }
    )

}

@Composable
private fun HomeFeed(
    modifier: Modifier,
    newsFeed: List<NewsArticle>?,

){
    newsFeed?.let { newsArticles ->
        LazyColumn(modifier){
            items(newsArticles.size){
                Text(text = newsArticles[it].title ?: "No Title")
            }
        }

    }?: Text(text = "No News Found")
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





