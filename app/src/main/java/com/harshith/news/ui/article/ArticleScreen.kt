package com.harshith.news.ui.article
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.model.NewsArticle
import com.harshith.news.model.newsArticle
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.util.addRegex
import com.harshith.news.util.parseTime

const val TAG = "ArticleScreen"
@Composable
fun ArticleScreen(
    newsArticle: NewsArticle,
    isBookmarked: Boolean,
    onBack: () -> Unit,
){
   Column(
       modifier = Modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())
           .padding(horizontal = 12.dp)
   ) {
       AppBar(
           Modifier.fillMaxWidth(),
           true,
           onBack
       )
       newsArticle.title?.let {
           Text(
               text = it,
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight.Bold,
               letterSpacing = TextUnit(value = 1f, type = TextUnitType.Sp),
               modifier = Modifier
                   .fillMaxWidth()
           )
       }
       Spacer(modifier = Modifier.padding(top = 4.dp))
       ArticleAuthorAndPublishedOn(newsArticle.creator, newsArticle.pubDate)
       Spacer(modifier = Modifier.padding(top = 8.dp))
       newsArticle.imageUrl?.let {
           ArticleImage(
               Modifier
                   .fillMaxWidth()
                   .aspectRatio(1.5f)
                   .clip(MaterialTheme.shapes.extraLarge),
               it)
       }
       Spacer(modifier = Modifier.padding(vertical = 4.dp))
       newsArticle.description?.let {
           Text(
               text = it,
               style = MaterialTheme.typography.bodyLarge,
               textAlign = TextAlign.Justify,
               modifier = Modifier
                   .fillMaxWidth()
           )
       }
       Spacer(modifier = Modifier.padding(vertical = 4.dp))
       newsArticle.content?.let {
           Text(
               buildAnnotatedString {
               append(it.addRegex())
               withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)){
                   append("read more")
               }
           })
       }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier,
    isBookmarked: Boolean,
    onBack: () -> Unit
){
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = if(isBookmarked)painterResource(id = R.drawable.ic_unbookmark) else painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null)
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

@Composable
fun ArticleAuthorAndPublishedOn(author: String?, publishedOn: String?){
    Row(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        author?.let {
            Text(
                text = it,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall
            )
        }
        publishedOn?.let {
            Text(
                text = parseTime(it),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@Preview
@Composable
fun PreviewArticleScreen(){
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ArticleScreen(
                newsArticle = newsArticle,
                isBookmarked = true,
                onBack = {  },)
        }
    }
}
