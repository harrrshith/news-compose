package com.harshith.news.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.data.Result
import com.harshith.news.data.posts.BlockingFakePostRepository
import com.harshith.news.data.posts.post1
import com.harshith.news.data.posts.post2
import com.harshith.news.data.posts.post3
import com.harshith.news.data.posts.post4
import com.harshith.news.data.posts.post5
import com.harshith.news.data.posts.posts
import com.harshith.news.model.Post
import com.harshith.news.model.PostsFeed
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
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
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
        val contentPadding = rememberContentPaddingForScreen(additionalTop = 16.dp)
        Row(contentModifier) {
            PostList(
                postsFeed = hasPostsUiState.postsFeed,
                favourites = hasPostsUiState.favourites,
                showExpandedSearch = !showTopAppBar,
                onArticleTapped = onSelectPosts,
                onToggleFavourite = onToggleFavourite,
                onSearchInputChanged = onSearchInputChanged,
                contentPadding = contentPadding,
                modifier = Modifier
                    .width(334.dp)
                    .notifyInput { onInteractWithList() }
                    .imePadding(),
                state = homeListLazyListState,
                searchInput = hasPostsUiState.searchInput
            )
            Crossfade(targetState = hasPostsUiState.selectedPost, label = "", animationSpec = tween(durationMillis = 2000, easing = FastOutLinearInEasing)) { detailPost ->
                val detailLazyListState by derivedStateOf {
                    articleDetailsLazyListStates.getValue(detailPost.id)
                }
                key(detailPost.id) {
                    LazyColumn(
                        state = detailLazyListState,
                        contentPadding = contentPadding,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                            .notifyInput {
                                onInteractWithDetail(detailPost.id)
                            }
                            .imePadding(),
                    ){
                        stickyHeader {
                            val context = LocalContext.current
                            PostTopBar(
                                isFavourite = hasPostsUiState.favourites.contains(detailPost.id),
                                onToggleFavourite = { onToggleFavourite(detailPost.id) },
                                onSharePost = { sharePost(detailPost, context) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                            )
                        }
                        postContentItems(detailPost)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    searchInput: String = "",
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
    ) {hasPostsUiState, contentModifier ->
        PostList(
            postsFeed = hasPostsUiState.postsFeed,
            favourites = hasPostsUiState.favourites,
            showExpandedSearch = !showTopAppBar,
            onArticleTapped = onSelectPosts,
            onToggleFavourite = onToggleFavourite,
            modifier = contentModifier,
            contentPadding = rememberContentPaddingForScreen(
                additionalTop = if(showTopAppBar) 0.dp else 8.dp,
                excludeTop = showTopAppBar
            ),
            state = homeListLazyListState,
            searchInput = searchInput,
            onSearchInputChanged = onSearchInputChanged,

        )
    }
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
                onRefreshPostsState()
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
        if(postsFeed.recommendedPosts.isNotEmpty()){
            item {
                PostListSimpleSelection(
                    posts = listOf(post1, post2, post3, post4, post5, post1, post2, post3, post4, post5),
                    navigateToArticle = onArticleTapped,
                    favourites = favourites,
                    onToggleFavourite = onToggleFavourite
                )
            }
        }

        if(postsFeed.popularPosts.isNotEmpty()){
            item { PostListPopularSection(posts = postsFeed.popularPosts, navigateToArticle = onArticleTapped) }
        }

        if(postsFeed.recentPosts.isNotEmpty()){
            item { PostListHistorySection(posts = postsFeed.recentPosts, navigateToArticle = onArticleTapped) }
        }
    }
}

@Composable //First Section
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

@OptIn(ExperimentalFoundationApi::class)
@Composable // Second Section
fun PostListSimpleSelection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit,
    favourites: Set<String>,
    onToggleFavourite: (String) -> Unit
){
    val state = rememberLazyGridState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        modifier = Modifier.heightIn(min = 250.dp, max = 350.dp),
        state = state,
        flingBehavior = flingBehavior
    ){
        items(posts.size){postIndex ->
            PostCardSimple(
                post = posts[postIndex],
                navigateToArticle = { navigateToArticle(posts[postIndex].id) },
                isFavourite = favourites.contains(posts[postIndex].id),
                onToggleFavourite = { onToggleFavourite(posts[postIndex].id) }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostListPopularSection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit
){
    val state = rememberLazyListState()
    val snappingLayout = remember(state) { SnapLayoutInfoProvider(state) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)
    Column {
        Text(
            text = stringResource(id = R.string.popular_on_news),
            modifier =  Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            state = state,
            flingBehavior = flingBehavior
        ){
            items(posts){ post ->
                PostCardPopular(
                    post,
                    navigateToArticle
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PostListDivider()
    }
}

@Composable
fun PostListHistorySection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit
){
    Column {
        posts.forEach { post ->
            PostCardHistory(
                post,
                navigateToArticle
            )
            PostListDivider()
        }
    }
}

@Composable
fun PostTopBar(
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit,
    onSharePost: () -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            Dp.Hairline,
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ),
        modifier = modifier.padding(0.dp, 0.dp, 16.dp, 0.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp, 0.dp)) {
            FavouriteButton(onClick = {} )
            BookMarkButton(isBookmarked = isFavourite, onClick = { onToggleFavourite() })
            ShareButton(onClick = onSharePost)
            TextSettingsButton(onClick = {})
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
        scrollBehavior = scrollBehaviour,
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeSearch(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit
){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyBoardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = searchInput,
        onValueChange = onSearchInputChanged,
        placeholder = { Text(text = stringResource(id = R.string.search_articles)) },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        modifier = modifier
            .fillMaxWidth()
            .interceptKey(Key.Enter) {
                submitSearch(onSearchInputChanged, context)
                keyBoardController?.hide()
                focusManager.clearFocus(force = true)
            },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                submitSearch(onSearchInputChanged, context)
                keyBoardController?.hide()
            }
        )
    )
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

private fun Modifier.notifyInput(block: () -> Unit): Modifier =
    composed {
        val blockState = rememberUpdatedState(newValue = block)
        pointerInput(Unit){
            while (currentCoroutineContext().isActive){
                awaitPointerEventScope {
                    awaitPointerEvent(PointerEventPass.Initial)
                    blockState.value()
                }
            }
        }
    }

private fun submitSearch(
    onSearchInputChanged: (String) -> Unit,
    context: Context
){

}

@Preview("Home list drawer screen")
@Preview("Home list drawer screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Home list drawer screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewHomeListDrawerScreen(){
    val postsFeed = runBlocking {
        (BlockingFakePostRepository().getPostsFeed() as Result.Success).data
    }
    NewsTheme {
        HomeFeedScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = posts,
                selectedPost = postsFeed.highlightedPost,
                isArticleOpen = false,
                favourites = emptySet(),
                isLoading = false,
                errorMessage = emptyList(),
                searchInput = ""
            ),
            showTopAppBar = true,
            onToggleFavourite = {},
            onSelectPosts = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            snackbarHostState = SnackbarHostState(),
            onSearchInputChanged ={}
        )
    }
}

@Preview("Home List nav rail Screen", device = Devices.NEXUS_7_2013)
@Preview(
    "Home list nav rail screen (dark)",
    uiMode = UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_7_2013
)
@Preview("Home list nav rail screen (big font)", fontScale = 1.5f, device = Devices.NEXUS_7_2013)
@Composable
fun PreviewNavListHomeRail(){
    val postsFeed = runBlocking {
        (BlockingFakePostRepository().getPostsFeed() as Result.Success).data
    }
    NewsTheme {
        HomeFeedScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = posts,
                selectedPost = postsFeed.highlightedPost,
                isArticleOpen = false,
                favourites = emptySet(),
                isLoading = false,
                errorMessage = emptyList(),
                searchInput = ""
            ),
            showTopAppBar = true,
            onToggleFavourite = {},
            onSelectPosts = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            snackbarHostState = SnackbarHostState(),
            onSearchInputChanged ={}
        )
    }
}

@Preview("Home List details screen", device = Devices.PIXEL_C)
@Preview("Home list detail screen (dark)", uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Home list detail screen (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewHomeListSimpleSelection(){
    val postsFeed = runBlocking {
        (BlockingFakePostRepository().getPostsFeed() as Result.Success).data
    }
    NewsTheme {
        HomeFeedWithArticleDetailsScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = posts,
                selectedPost = postsFeed.highlightedPost,
                isArticleOpen = false,
                favourites = emptySet(),
                isLoading = false,
                errorMessage = emptyList(),
                searchInput = ""
            ),
            showTopAppBar = true,
            onToggleFavourite = {},
            onSelectPosts = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            onInteractWithList = {},
            onInteractWithDetail = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            articleDetailsLazyListStates = posts
                .allPosts.associate {
                    key(it.id) {
                        it.id to rememberLazyListState()
                    }
            },
            snackbarHostState = SnackbarHostState(),
            onSearchInputChanged = {}
        )
    }
}

@Preview
@Composable
fun PreviewPostListTopSelection(){
    NewsTheme {
        Surface {
            PostListTopSelection(
                post = post1,
                navigateToArticle = {}
            )
        }
    }
}