package com.harshith.news.ui.home

import android.view.translation.UiTranslationStateCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.ui.utils.logE

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
    HomeRoute(
        isExpandedScreen = isExpandedScreen,
        onToggleFavourite = { homeViewModel.toggleFavourites(it) },
        onSelectPosts = { navigateToArticleDetail(it) },
        onSelectPostsInLargeScreen = { homeViewModel.selectArticle(it) },
        onInteractWithFeed = { homeViewModel.interactWithFeed() },
        onInteractWidthArticleDetails = { homeViewModel.interactWithArticleDetails(it) },
        onSearchInputChange = { homeViewModel.onSearchInputChanged(it) },
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState
    )

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





