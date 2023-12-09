package com.harshith.news.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.previewData.article
import com.harshith.news.model.news.Article
import com.harshith.news.ui.article.postContentItems
import com.harshith.news.ui.article.sharePost
import com.harshith.news.ui.modifier.interceptKey
import com.harshith.news.ui.rememberContentPaddingForScreen
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.ui.utils.BookMarkButton
import com.harshith.news.ui.utils.FavouriteButton
import com.harshith.news.ui.utils.ShareButton
import com.harshith.news.ui.utils.TextSettingsButton
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive

//fun HomeFeedWithArticleDetailsScreen(
//    showTopAppBar: Boolean,
//    onToggleFavourite: (String) -> Unit,
//    onSelectPosts: (String) -> Unit,
//    onRefreshPosts: () -> Unit,
//    onErrorDismiss: (Long) -> Unit,
//    onInteractWithList: () -> Unit,
//    onInteractWithDetail: (String) -> Unit,
//    openDrawer: () -> Unit,
//    homeListLazyListState: LazyListState,
//    articleDetailsLazyListStates: Map<String?,LazyListState?>,
//    snackbarHostState: SnackbarHostState,
//    modifier: Modifier = Modifier,
//    onSearchInputChanged: (String) -> Unit
//    ){
//    HomeScreenWithList(
//        showTopAppBar = showTopAppBar,
//        onRefreshPosts = { onRefreshPosts() },
//        onErrorDismiss = onErrorDismiss,
//        openDrawer = { openDrawer() },
//        snackbarHostState = snackbarHostState,
//        modifier = modifier
//    ){
//        val contentPadding = rememberContentPaddingForScreen(additionalTop = 16.dp)
//        Row(contentModifier) {
//            PostList(
//                newsFeed = hasPostsUiState.newsFeed!!,
//                favourites = hasPostsUiState.favourites,
//                showExpandedSearch = !showTopAppBar,
//                onArticleTapped = onSelectPosts,
//                onToggleFavourite = onToggleFavourite,
//                onSearchInputChanged = onSearchInputChanged,
//                contentPadding = contentPadding,
//                modifier = Modifier
//                    .width(334.dp)
//                    .notifyInput { onInteractWithList() }
//                    .imePadding(),
//                state = homeListLazyListState,
//                searchInput = hasPostsUiState.searchInput
//            )
//            Crossfade(
//                targetState = hasPostsUiState.selectedPost,
//                label = "",
//                animationSpec = tween(durationMillis = 2000, easing = FastOutLinearInEasing)
//            ) { newsDetail ->
//                val detailLazyListState by derivedStateOf {
//                    articleDetailsLazyListStates.getValue("")
//                }
//                key(newsDetail) {
//                    LazyColumn(
//                        state = detailLazyListState!!,
//                        contentPadding = contentPadding,
//                        modifier = Modifier
//                            .padding(horizontal = 16.dp)
//                            .fillMaxSize()
//                            .notifyInput {
//                                onInteractWithDetail("")
//                            }
//                            .imePadding(),
//                    ){
//                        stickyHeader {
//                            val context = LocalContext.current
//                            PostTopBar(
//                                isFavourite = hasPostsUiState.favourites.contains(""),
//                                onToggleFavourite = { onToggleFavourite("") },
//                                onSharePost = { sharePost(article, context) },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .wrapContentWidth(Alignment.End)
//                            )
//                        }
//                        postContentItems(article)
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun HomeFeedScreen(
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
){
    HomeScreenWithList(
        showTopAppBar = showTopAppBar,
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState,
    )
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
        Text(text = "Started!!")
    }
//    val topAppBarState = rememberTopAppBarState()
//    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
//    Scaffold(
//        topBar = {
//            if(showTopAppBar){
//                HomeTopAppBar(
//                    openDrawer = openDrawer,
//                    topAppBarState = topAppBarState
//                )
//            }
//        },
//        modifier = modifier
//
//    ){innerPadding ->
//        val contentModifier = modifier
//            .padding(innerPadding)
//            .nestedScroll(scrollBehaviour.nestedScrollConnection)
//        LoadingContent(
//            empty = when (uiState) {
//                    is HomeUiState.HasPosts -> false
//                    is HomeUiState.NoPosts -> uiState.isLoading
//            },
//            emptyContent = { FullScreenLoading() },
//            loading = uiState.isLoading,
//            onRefresh = { onRefreshPosts() },
//            content =  {
//                when(uiState){
//                    is HomeUiState.HasPosts -> hasPostsContent(uiState, contentModifier)
//                    is HomeUiState.NoPosts -> {
//                        if(uiState.errorMessage.isEmpty()){
//                            TextButton(
//                                onClick = { onRefreshPosts() },
//                                modifier.fillMaxSize()
//                            ) {
//                                Text(
//                                    text = stringResource(id = R.string.click_here),
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }else{
//                            Box(
//                                modifier = contentModifier.fillMaxSize()
//                            ){}
//                        }
//                    }
//                }
//            }
//        )
//    }
//    /**
//     * When the viewModelState emits the error then this block of executes.
//     * LaunchedEffect is used to call the snackbar inside the composable
//     */
//    if(uiState.errorMessage.isNotEmpty()){
//        val errorMessage = remember(uiState){
//            uiState.errorMessage[0]
//        }
//        val errorMessageText: String = stringResource(errorMessage.messageId)
//        val retryTextMessage = stringResource(id = R.string.retry)
//
//        val onRefreshPostsState by rememberUpdatedState(newValue = onRefreshPosts)
//        val onErrorDismissState by rememberUpdatedState(newValue = onErrorDismiss)
//
//        LaunchedEffect(errorMessageText, retryTextMessage, snackbarHostState){
//            val snackBarResult = snackbarHostState.showSnackbar(
//                message = errorMessageText,
//                actionLabel = retryTextMessage
//            )
//            if(snackBarResult == SnackbarResult.ActionPerformed){
//                onRefreshPostsState()
//            }
//            onErrorDismissState(errorMessage.id)
//        }
//    }
}


