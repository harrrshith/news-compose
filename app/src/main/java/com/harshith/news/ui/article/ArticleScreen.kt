package com.harshith.news.ui.article
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticle
import com.harshith.news.ui.theme.NewsTheme

const val TAG = "ArticleScreen"
@Composable
fun ArticleScreen(
    newsArticle: NewsArticle,
    onBack: () -> Unit,
){
   Column(
       modifier = Modifier
           .fillMaxSize()
   ) {
       AppBar(Modifier.fillMaxWidth(), onBack)
       newsArticle.imageUrl?.let {
           ArticleImage(
               Modifier
                   .fillMaxWidth()
                   .aspectRatio(1.5f)
                   .padding(horizontal = 4.dp)
                   .clip(MaterialTheme.shapes.extraLarge),
               it)
       }
       Spacer(modifier = Modifier.padding(top = 8.dp))
       newsArticle.title?.let {
           Text(
               text = it,
               style = MaterialTheme.typography.titleLarge,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 8.dp)
           )
       }
       Spacer(modifier = Modifier.padding(vertical = 4.dp))
       newsArticle.content?.let {
           Text(
               text = it,
               style = MaterialTheme.typography.bodyMedium,
               textAlign = TextAlign.Justify,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 8.dp)
           )
       }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier,
    onBack: () -> Unit
){
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun ArticleImage(modifier: Modifier, imageUrl: String){
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = "News Article Image",
        contentScale = ContentScale.FillBounds
    )
}

@Preview(apiLevel = 33)
@Composable
fun PreviewArticleScreen(){
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ArticleScreen(
                newsArticle = newsArticle,
                onBack = {  },)
        }
    }
}
