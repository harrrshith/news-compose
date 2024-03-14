package com.harshith.news.ui.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.model.NewsArticle

const val TAG = "HOME_ROUTE"
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    navigateToArticleDetail: (NewsArticle) -> Unit,
    snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    },
){
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeRoute(
        uiState = uiState,
        viewModel = homeViewModel,
        isExpandedScreen = isExpandedScreen,
        onToggleFavourite = { homeViewModel.toggleFavourites(it) },
        onSelectPosts = { navigateToArticleDetail(it) },
        onSelectPostsInLargeScreen = { homeViewModel.selectArticle(it) },
        onInteractWithFeed = { homeViewModel.interactWithFeed() },
        onInteractWidthArticleDetails = { homeViewModel.interactWithArticleDetails(it) },
        onSearchInputChange = { homeViewModel.onSearchInputChanged(it) },
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState,
        getNewsByCategory = { homeViewModel.getNewsByCategory(it) }
    )

}
@Composable
fun HomeRoute(
    uiState: HomeUiState,
    viewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (NewsArticle) -> Unit,
    onSelectPostsInLargeScreen: (String) -> Unit,
    onInteractWithFeed: () -> Unit,
    onInteractWidthArticleDetails: (String) -> Unit,
    onSearchInputChange: (String) -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    getNewsByCategory: (String) -> Unit
){
    val homeLazyListState = rememberLazyListState()
    if(isExpandedScreen){
        HomeFeedWithArticleDetailsScreen(
            uiState = uiState,
            showTopAppBar = false,
            onToggleFavourite = onToggleFavourite,
            onSelectPosts = onSelectPostsInLargeScreen,
            onRefreshPosts = {  },
            onInteractWithList = { onInteractWithFeed() },
            onInteractWithDetail = onInteractWidthArticleDetails,
            openDrawer = { openDrawer() },
            homeListLazyListState = homeLazyListState,
            snackbarHostState = snackbarHostState,
            onSearchInputChanged = {onSearchInputChange(it)}
        )
    }
    else{
        HomeFeedScreen(
            uiState = uiState,
            showTopAppBar = true,
            onSelectPosts = { onSelectPosts(it)},
            openDrawer = { openDrawer() },
            getNewsByCategory = getNewsByCategory
        )
    }
}





