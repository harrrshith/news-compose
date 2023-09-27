package com.harshith.news.ui.article

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.harshith.news.R
import com.harshith.news.data.posts.post1
import com.harshith.news.data.previewData.article
import com.harshith.news.model.Markup
import com.harshith.news.model.MarkupType
import com.harshith.news.model.MetaData
import com.harshith.news.model.Paragraph
import com.harshith.news.model.ParagraphType
import com.harshith.news.model.Post
import com.harshith.news.model.news.Article
import com.harshith.news.model.news.Source
import com.harshith.news.ui.theme.NewsTheme
import com.harshith.news.util.Constants
import com.harshith.news.util.parseTime

private val defaultSpacerSize = 16.dp
@Composable
fun PostContent(
    article: Article,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState()
){
    LazyColumn(
        contentPadding = PaddingValues(defaultSpacerSize),
        modifier = modifier,
        state = state
    ){
        postContentItems(article = article)
    }
}

fun LazyListScope.postContentItems(article: Article){
    item {
        PostHeaderImage(article = article)
        Spacer(modifier = Modifier.height(defaultSpacerSize))
        Text(text = article.title ?: "", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        article.description?.let {description ->
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(defaultSpacerSize))
        }
    }
    item { PostMetaData(authorName = article.author!!, publishedData = article.publishedAt!!, modifier = Modifier.padding(bottom = 24.dp))}
    //items(article.paragraphs){ Paragraph(paragraph = it) }
}

@Composable
fun PostHeaderImage(article: Article){
    val modifier = Modifier
        .heightIn(min = 180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)

    AsyncImage(
        model = article.urlToImage ?: Constants.PLACEHOLDER_IMAGE,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostMetaData(
    authorName: String,
    publishedData: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {}
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            Text(
                text = authorName,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.post_min_read,
                    formatArgs = arrayOf(
                        parseTime(publishedData),
                        "8"
                    )
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
/*
@Composable
fun Paragraph(paragraph: Paragraph){
    val (textStyle, paragraphStyle, trailingPadding) = paragraph.type.getTextAndParagraphStyle()
    val annotatedString = paragraphToAnnotatedString(
        paragraph = paragraph,
        typography = MaterialTheme.typography,
        codeBlockBackground = MaterialTheme.colorScheme.codeColorBackground
    )
    Box(modifier = Modifier.padding(bottom = trailingPadding)){
        when(paragraph.type){
            ParagraphType.Bullet -> BulletParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle
            )
            ParagraphType.CodeBlock -> CodeBlockParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle
            )
            ParagraphType.Header -> {
                Text(
                    text = annotatedString,
                    style = textStyle.merge(paragraphStyle),
                    modifier = Modifier.padding(4.dp)
                )
            }
            else -> Text(
                text = annotatedString,
                style = textStyle.merge(paragraphStyle),
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}

@Composable
private fun CodeBlockParagraph(
  text: AnnotatedString,
  textStyle: TextStyle,
  paragraphStyle: ParagraphStyle
){
    Surface(
        color = MaterialTheme.colorScheme.codeColorBackground,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = textStyle.merge(paragraphStyle)
        )
    }
}

@Composable
private fun BulletParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle
){
    Row {
        with(LocalDensity.current){
            Box(
                modifier = Modifier
                    .size(8.sp.toDp(), 8.sp.toDp())
                    .alignBy {
                        9.sp.roundToPx()
                    }
                    .background(LocalContentColor.current, RectangleShape)
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .alignBy(FirstBaseline),
            style = textStyle.merge(paragraphStyle)
        )
    }
}

private data class ParagraphStyling(
    val textStyle: TextStyle,
    val paragraphStyle: ParagraphStyle,
    val trailingPadding: Dp
)

@Composable
private fun ParagraphType.getTextAndParagraphStyle(): ParagraphStyling{
    val typography = MaterialTheme.typography
    var textStyle = typography.bodyLarge
    var paragraphStyle = ParagraphStyle()
    var trailingPadding = 24.dp

    when(this){
        ParagraphType.Caption -> textStyle = typography.labelMedium
        ParagraphType.Title -> textStyle = typography.headlineLarge
        ParagraphType.Subhead -> {
            textStyle = typography.headlineSmall
            trailingPadding = 16.dp
        }
        ParagraphType.Text -> textStyle = typography.bodyLarge.copy(lineHeight = 28.sp)
        ParagraphType.Header -> {
            textStyle = typography.headlineMedium
            trailingPadding = 16.dp
        }
        ParagraphType.CodeBlock -> {
            textStyle = typography.bodyLarge.copy(fontFamily = FontFamily.Monospace)
        }
        ParagraphType.Quote -> textStyle = typography.bodyLarge
        ParagraphType.Bullet -> {
            paragraphStyle = ParagraphStyle(textIndent = TextIndent(firstLine = 8.sp))
        }
    }
    return ParagraphStyling(
        textStyle = textStyle,
        paragraphStyle = paragraphStyle,
        trailingPadding = trailingPadding
    )
}

private fun paragraphToAnnotatedString(
    paragraph: Paragraph,
    typography: Typography,
    codeBlockBackground: Color
): AnnotatedString {
    val styles: List<AnnotatedString.Range<SpanStyle>> = paragraph.markups
        .map { it.toAnnotatedStringItem(typography, codeBlockBackground) }
    return AnnotatedString(text = paragraph.text, spanStyles = styles)
}


fun Markup.toAnnotatedStringItem(
    typography: Typography,
    codeBlockBackground: Color
): AnnotatedString.Range<SpanStyle>{
    return when(this.type){
        MarkupType.Italic -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(fontStyle = FontStyle.Italic).toSpanStyle(),
                start = start,
                end = end
            )
        }
        MarkupType.Code -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(
                    background = codeBlockBackground,
                    fontFamily = FontFamily.Monospace
                ).toSpanStyle(),
                start = start,
                end = end
            )
        }
        MarkupType.Link -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(textDecoration = TextDecoration.Underline).toSpanStyle(),
                start,
                end
            )
        }
        MarkupType.Bold -> {
            AnnotatedString.Range(
                typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
                start,
                end
            )
        }
    }
}
*/
private val ColorScheme.codeColorBackground: Color
    get() = onSurface.copy(alpha = .15f)

@Preview
@Composable
fun PreviewPostContent(){
    NewsTheme {
        Surface {
            PostContent(
                article = article
            )
        }
    }
}