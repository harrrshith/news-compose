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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
    Column {
        if(showTopAppBar){
            NewsTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                openDrawer
            )
        }
        newsFeed?.let { newsArticles ->
            if (newsArticles.isNotEmpty())
                TopHeadlines(newsArticle = newsArticles, lazyListState = lazyListState, screenWidth = screenWidth)
            NewsTabs(
                tabTitles,
                Modifier
                    .padding(top = 8.dp)
                    .shadow(0.dp)
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
            NewsCard(newsArticle = newsArticle[index], screenWidth)
        }
    }
}

@Composable
fun NewsTabs(
    tabTitles: List<String>,
    modifier: Modifier
){
    var tabIndex by remember {
        mutableIntStateOf(0)
    }
    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        edgePadding = 0.dp,
        modifier = modifier,
        indicator = { tabPositions ->
        TabRowDefaults.Indicator(
            modifier = Modifier
                .tabIndicatorOffset(tabPositions[tabIndex])
                .alpha(.1f)
                .clip(MaterialTheme.shapes.extraLarge),
            height = 50.dp,
        )},
        divider = {

        }
    ){
        tabTitles.forEachIndexed { index, tabItem ->
            Tab(
                selected = tabIndex == index,
                onClick = { tabIndex = index },
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 4.dp)
            ) {
                Text(text = tabItem)
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

