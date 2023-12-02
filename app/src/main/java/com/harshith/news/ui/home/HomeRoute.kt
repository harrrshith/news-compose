package com.harshith.news.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
    HomeRoute(
        uiState = uiState,
        isExpandedScreen = isExpandedScreen,
        onToggleFavourite = { homeViewModel.toggleFavourites(it) },
        onSelectPosts = { navigateToArticleDetail(it) },
        onSelectPostsInLargeScreen = { homeViewModel.selectArticle(it) },
        onRefreshPosts = {  },
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
    onSelectPostsInLargeScreen: (String) -> Unit,
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
        is HomeUiState.HasPosts -> uiState.newsFeed?.allFeedNews
        is HomeUiState.NoPosts -> emptyList()
    }?.associate { article ->
        key(article.uuid){
            article.title to rememberLazyListState()
        }
    }

    when(getHomeScreenType(isExpandedScreen, uiState)){
        HomeScreenType.FeedWithArticles -> {
            HomeFeedWithArticleDetailsScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavourite = onToggleFavourite,
                onSelectPosts = onSelectPostsInLargeScreen,
                onRefreshPosts = { onRefreshPosts() },
                onErrorDismiss = onErrorDismiss,
                onInteractWithList = { onInteractWithFeed() },
                onInteractWithDetail = onInteractWidthArticleDetails,
                openDrawer = { openDrawer() },
                homeListLazyListState = homeLazyListState,
                articleDetailsLazyListStates = articleDetailsLazyListState!!,
                snackbarHostState = snackbarHostState,
                onSearchInputChanged = onSearchInputChange
            )
        }
        HomeScreenType.Feed -> {
            HomeFeedScreen(
                uiState = uiState,
                showTopAppBar = !isExpandedScreen,
                onToggleFavourite = onToggleFavourite,
                onSelectPosts = { onSelectPosts(it)},
                onRefreshPosts = { onRefreshPosts() },
                onErrorDismiss = onErrorDismiss,
                openDrawer = { openDrawer() },
                homeListLazyListState = homeLazyListState,
                snackbarHostState = snackbarHostState,
                onSearchInputChanged = onSearchInputChange
            )
        }
        HomeScreenType.ArticleDetails -> {
            check(uiState is HomeUiState.HasPosts)
//            ArticleScreen(
//                article = ,
//                isExpandedScreen = isExpandedScreen,
//                onBack = { onInteractWithFeed() },
//                isFavourite = uiState.favourites.contains(uiState.selectedPost.id),
//                onToggleFavourite = { onToggleFavourite(uiState.selectedPost.id) },
//                lazyListState = articleDetailsLazyListState.getValue(
//                    uiState.selectedPost.id
//                )
//            )

            BackHandler {
                onInteractWithFeed()
            }
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