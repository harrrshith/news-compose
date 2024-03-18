package com.harshith.news.ui.interests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticle
import com.harshith.news.ui.home.NewsCardVertical
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
        val newsArticle = uiState.newsArticles
        newsArticle?.let {_ ->
            if (newsArticle.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Wow so empty!")
                }
            }
            else{
                LazyColumn {
                    items(newsArticle.size){
                        NewsCardVertical(newsArticle = newsArticle[it], onArticleClick)
                    }
                }
            }
        }
    }


}

