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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.NewsFeed
import com.harshith.news.util.logE
import com.harshith.news.util.logV
import kotlinx.coroutines.launch

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
    onSelectPosts: (String) -> Unit,
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
            viewModel = viewModel,
            showTopAppBar = true,
            onToggleFavourite = onToggleFavourite,
            onSelectPosts = { onSelectPosts(it)},
            openDrawer = { openDrawer() },
            homeListLazyListState = homeLazyListState,
            snackbarHostState = snackbarHostState,
            getNewsByCategory = getNewsByCategory
        )
    }
}





