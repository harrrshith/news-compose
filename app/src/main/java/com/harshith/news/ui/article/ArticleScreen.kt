import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.SerializedNewsArticle
import com.harshith.news.util.logE

//package com.harshith.news.ui.article
const val TAG = "ArticleScreen"
@Composable
fun ArticleScreen(
    newsArticle: SerializedNewsArticle,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    isFavourite: Boolean,
    onToggleFavourite: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
){
    TAG.logE("$newsArticle")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Hello", )
    }

}
