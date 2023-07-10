package com.harshith.news.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.posts.posts
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
import com.harshith.news.ui.theme.NewsTheme

@Composable
fun HomeFeedWithArticleDetailsScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithList: () -> Unit,
    onInteractWithDetail: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    articleDetailsLazyListStates: Map<String,LazyListState>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onSearchInputChanged: (String) -> Unit
    ){
    HomeScreenWithList(
        uiState = uiState,
        showTopAppBar = showTopAppBar,
        onRefreshPosts = { onRefreshPosts() },
        onErrorDismiss = onErrorDismiss,
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState,
        modifier = modifier
    ){hasPostsUiState, contentModifier ->


    }

}

@Composable
fun HomeFeedScreen(

){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenWithList(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    hasPostsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
){
    val topAppBarState = rememberTopAppBarState()
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            if(showTopAppBar){
                HomeTopAppBar(
                    openDrawer = openDrawer,
                    topAppBarState = topAppBarState
                )
            }
        },
        modifier = modifier

    ){innerPadding ->
        val contentModifier = modifier
            .padding(innerPadding)
            .nestedScroll(scrollBehaviour.nestedScrollConnection)
        LoadingContent(
            empty = when (uiState) {
                    is HomeUiState.HasPosts -> false
                    is HomeUiState.NoPosts -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = { onRefreshPosts() },
            content =  {
                when(uiState){
                    is HomeUiState.HasPosts -> hasPostsContent(uiState, contentModifier)
                    is HomeUiState.NoPosts -> {
                        if(uiState.errorMessage.isEmpty()){
                            TextButton(
                                onClick = { onRefreshPosts() },
                                modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.click_here),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }else{
                            Box(
                                modifier = contentModifier.fillMaxSize() 
                            ){}
                        }
                    }
                }
            }
        )
    }
    if(uiState.errorMessage.isNotEmpty()){
        val errorMessage = remember(uiState){
            uiState.errorMessage[0]
        }
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryTextMessage = stringResource(id = R.string.retry)

        val onRefreshPostsState by rememberUpdatedState(newValue = onRefreshPosts)
        val onErrorDismissState by rememberUpdatedState(newValue = onErrorDismiss)

        LaunchedEffect(errorMessageText, retryTextMessage, snackbarHostState){
            val snackBarResult = snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryTextMessage
            )
            if(snackBarResult == SnackbarResult.ActionPerformed){
                onRefreshPosts()
            }
            onErrorDismissState(errorMessage.id)
        }
    }
}

@Composable
fun PostList(
    postsFeed: PostsFeed,
    favourites: Set<String>,
    showExpandedSearch: Boolean,
    onArticleTapped: (postId: String) -> Unit,
    onToggleFavourite: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = LazyListState(),
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state
    ){
        if(showExpandedSearch){
            item {
                HomeSearch(
                    Modifier.padding(horizontal = 16.dp),
                    searchInput = searchInput,
                    onSearchInputChanged = onSearchInputChanged
                )
            }
        }
        item { PostListTopSelection( postsFeed.highlightedPost, onArticleTapped) }
    }
}

@Composable
fun PostListTopSelection(
    post: Post,
    navigateToArticle: (String) -> Unit
){
    Text(
        text = stringResource(id = R.string.top_stories_for_you),
        modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp),
        style = MaterialTheme.typography.titleMedium
    )
    PostCardTop(
        post,
        modifier = Modifier.clickable(onClick = { navigateToArticle(post.id) })
    )
    PostListDivider()
}

@Composable
fun PostListSimpleSelection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit,
    favourites: Set<String>,
    onToggleFavourite: (String) -> Unit
){
    Column {
        posts.forEach { post ->
            PostCardSimple(
                post = post,
                navigateToArticle = { navigateToArticle(post.id) },
                isFavourite = favourites.contains(post.id),
                onToggleFavourite = { onToggleFavourite(post.id) }
            )
        }
    }
}
@Composable
fun PostListDivider(){
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(0.05f)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    openDrawer: () -> Unit,
    topAppBarState: TopAppBarState,
    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState),
    modifier: Modifier = Modifier
){
    val title = stringResource(id = R.string.app_name)
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_news_title),
                contentDescription = title,
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Search,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
//        scrollBehavior = scrollBehaviour,
    modifier = modifier
    )
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
){
    if(empty){
        emptyContent()
    }else{
        content()
    }
}

@Composable
fun HomeSearch(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit
){

}

@Composable
fun FullScreenLoading(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ){
        CircularProgressIndicator()
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun PreviewHomeTopBar(){
    HomeTopAppBar(
        openDrawer = {},
        topAppBarState = TopAppBarState(1f, 1f, 1f),
    )
}