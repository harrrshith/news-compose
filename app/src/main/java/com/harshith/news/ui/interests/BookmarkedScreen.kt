package com.harshith.news.ui.interests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.harshith.news.model.NewsArticle
import com.harshith.news.ui.utils.NewsTopAppBar
import com.harshith.news.util.logE

private const val TAGG = "Interest Screen"
@Composable
fun BookmarkedScreen(
    uiState: BookmarkedUiState,
    isExpandedScreen: Boolean,
    onArticleClick: (NewsArticle) -> Unit,
    openDrawer: () ->Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NewsTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            openDrawer,
            "Bookmarked"
        )
    }
    TAGG.logE("${uiState.newsArticles}")

}

