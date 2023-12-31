package com.harshith.news.ui.utils

import android.widget.ImageButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import com.harshith.news.R

@Composable
fun BookMarkButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val clickLabel = stringResource( if(isBookmarked) R.string.unbookmark else R.string.bookmark)

    IconToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onClick() },
        modifier = modifier.semantics {
            this.onClick( label = clickLabel, action = null )
        }
    ) {
        Icon(
            painter = if(isBookmarked) painterResource(id = R.drawable.ic_unbookmark) else painterResource(id = R.drawable.ic_bookmark),
            contentDescription = null
        )
    }
}

@Composable
fun FavouriteButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = null
        )
    }
}

@Composable
fun ShareButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null
        )
    }
}

@Composable
fun TextSettingsButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            contentDescription = null
        )
    }
}