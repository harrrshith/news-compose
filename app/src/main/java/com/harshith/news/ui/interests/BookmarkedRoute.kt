package com.harshith.news.ui.interests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harshith.news.model.NewsArticle

@Composable
fun BookmarkedRoute(
    bookmarkedViewModel: BookmarkedViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
    onArticleClicked: (NewsArticle) -> Unit,
    openDrawer: () -> Unit,
){
    val uiState by bookmarkedViewModel.uiState.collectAsStateWithLifecycle()
    BookmarkedScreen(
        uiState = uiState,
        isExpandedScreen = isExpandedScreen,
        onArticleClick = onArticleClicked,
        openDrawer = { openDrawer() },
    )

}