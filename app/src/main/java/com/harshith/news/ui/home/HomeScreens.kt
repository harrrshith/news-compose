package com.harshith.news.ui.home
//Home screen will have a appbar which will be obviously material with all those animations.
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harshith.news.model.NewsArticle
import com.harshith.news.ui.utils.NewsTopAppBar

@Composable
fun HomeFeedWithArticleDetailsScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onInteractWithList: () -> Unit,
    onInteractWithDetail: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onSearchInputChanged: (String) -> Unit,
){
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello")
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(text = "Landscape")
    }
    
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onToggleFavourite: (String) -> Unit,
    onSelectPosts: (String) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    snackbarHostState: SnackbarHostState,
){
    val lazyListState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val newsFeed = when(uiState){
        is HomeUiState.HasNews -> uiState.newsFeed?.homeFeedNews
        is HomeUiState.NoNews -> emptyList()
    }
    val tabTitles = listOf("Sports", "Technology", "Entertainment", "Politics", "Others")
    val tabIndex = remember {
        mutableIntStateOf(0)
    }
    val pageState = rememberPagerState {
        tabTitles.size
    }
    Column() {
        if(showTopAppBar){
            NewsTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                openDrawer
            )
        }
        newsFeed?.let { newsArticles ->
            if (newsArticles.isNotEmpty())
                TopHeadlines(
                    newsArticle = newsArticles,
                    lazyListState = lazyListState,
                    screenWidth = screenWidth
                )
            NewsTabs(
                tabTitles = tabTitles,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .shadow(0.dp),
                tabIndex = tabIndex,
                pagerState = pageState
            )
            NewsPager(
                state = pageState,
                tabIndex = tabIndex,
                newsArticle = newsFeed,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopHeadlines(
    newsArticle: List<NewsArticle>,
    lazyListState: LazyListState,
    screenWidth: Dp
){
    Text(
        text = "Top Headlines",
        modifier = Modifier.padding(start = 12.dp, top = 8.dp),
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight(800)
    )
    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState)
    ){
        items(newsArticle.size){index ->
            NewsCardHorizontal(newsArticle = newsArticle[index], screenWidth)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsTabs(
    tabTitles: List<String>,
    modifier: Modifier,
    tabIndex: MutableIntState,
    pagerState: PagerState
){
    LaunchedEffect(tabIndex.intValue){
        pagerState.animateScrollToPage(tabIndex.intValue)
    }
    ScrollableTabRow(
        selectedTabIndex = tabIndex.intValue,
        edgePadding = 8.dp,
        modifier = modifier,
        indicator = { tabPositions ->
        TabRowDefaults.Indicator(
            modifier = Modifier
                .tabIndicatorOffset(tabPositions[tabIndex.intValue])
                .alpha(.1f)
                .clip(MaterialTheme.shapes.extraLarge),
            height = 50.dp,
        )},
        divider = {

        }
    ){
        tabTitles.forEachIndexed { index, tabItem ->
            Tab(
                selected = tabIndex.intValue == index,
                onClick = { tabIndex.intValue = index },
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 4.dp)
            ) {
                Text(text = tabItem, modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPager(
    state: PagerState,
    tabIndex: MutableIntState,
    newsArticle: List<NewsArticle>,
    modifier: Modifier
){
    LaunchedEffect(state.currentPage){
        tabIndex.intValue = state.currentPage
    }
    HorizontalPager(state = state, modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(top = 4.dp)
        ){
            items(newsArticle.size){
                NewsCardVertical(newsArticle = newsArticle[it])
            }
        }
    }
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
        Column {
            Text(text = "Hello World", style = MaterialTheme.typography.displayLarge)
            Text(text = "Hello World", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

