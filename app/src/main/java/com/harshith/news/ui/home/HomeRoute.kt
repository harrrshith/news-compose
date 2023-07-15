package com.harshith.news.ui.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.key

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }
){
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeRoute(
        uiState = uiState,
        isExpandedScreen = isExpandedScreen,
        onToggleFavourite = { homeViewModel.toggleFavourites(it) },
        onSelectPosts = { homeViewModel.selectArticle(it) },
        onRefreshPosts = { homeViewModel.refreshPosts() },
        onErrorDismiss = { },
        onInteractWithFeed = { homeViewModel.interactWithFeed() },
        onInteractWidthArticleDetails = { homeViewModel.interactWithArticleDetails(it) },
        onSearchInputChange = { homeViewModel.onSearchInputChanged(it) },
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState
    )

}

@Composable
fun HomeRoute(
    uiState: HomeUiState,
    isExpandedScreen: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithFeed: () -> Unit,
    onInteractWidthArticleDetails: (String) -> Unit,
    onSearchInputChange: (String) -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState
){
    val homeLazyListState = rememberLazyListState()
    val articleDetailsLazyListState = when(uiState){
        is HomeUiState.HasPosts -> uiState.postsFeed.allPosts
        is HomeUiState.NoPosts -> emptyList()
    }.associate { post ->
        key(post.id){
            post.id to rememberLazyListState()
        }
    }

    val homeScreenType = getHomeScreenType(isExpandedScreen, uiState)
    when(homeScreenType){
        HomeScreenType.FeedWithArticles -> {
            HomeFeedWithArticleDetailsScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavourite = onToggleFavourite,
                onSelectPosts = onSelectPosts,
                onRefreshPosts = { onRefreshPosts() },
                onErrorDismiss = onErrorDismiss,
                onInteractWithList = { onInteractWithFeed() },
                onInteractWithDetail = onInteractWidthArticleDetails,
                openDrawer = { openDrawer() },
                homeListLazyListState = homeLazyListState,
                articleDetailsLazyListStates = articleDetailsLazyListState,
                snackbarHostState = snackbarHostState,
                onSearchInputChanged = onSearchInputChange
            )
        }
        HomeScreenType.Feed -> {
            HomeFeedScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavourite = onToggleFavourite,
                onSelectPosts = onSelectPosts,
                onRefreshPosts = { onRefreshPosts() },
                onErrorDismiss = onErrorDismiss,
                openDrawer = { openDrawer() },
                homeListLazyListState = homeLazyListState,
                snackbarHostState = snackbarHostState,
                onSearchInputChanged = onSearchInputChange
            )
        }
        HomeScreenType.ArticleDetails -> {

        }
    }
}

private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    uiState: HomeUiState
): HomeScreenType = when(isExpandedScreen){
    false -> {
        when(uiState){
            is HomeUiState.HasPosts -> {
                if(uiState.isArticleOpen)
                    HomeScreenType.ArticleDetails
                else
                    HomeScreenType.Feed
            }
            is HomeUiState.NoPosts -> HomeScreenType.Feed
        }
    }
    true -> HomeScreenType.FeedWithArticles
}

enum class HomeScreenType{
    FeedWithArticles,
    Feed,
    ArticleDetails
}