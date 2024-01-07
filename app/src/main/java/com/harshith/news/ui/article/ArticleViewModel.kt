//package com.harshith.news.ui.article
//
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import com.harshith.news.data.previewData.article
//import com.harshith.news.data.repository.NewsRepository
//import com.harshith.news.model.news.Article
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//
//data class ArticleUiState(
//    val article: Article,
//    val isFavourite: Boolean = false
//)
//
//@HiltViewModel
//class ArticleViewModel @Inject constructor(
//    private val newsRepository: NewsRepository,
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//    private val _uiState = MutableStateFlow(ArticleUiState(
//        article = article,
//        isFavourite = false
//    ))
//    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()
//}