package com.harshith.news.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harshith.news.R
import com.harshith.news.ui.theme.NewsTheme

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet(modifier) {
        NewsNavigationLogo(
            modifier.padding(32.dp)
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.home)) },
            icon = { Icon( Icons.Filled.Home, null) },
            selected = currentRoute == NewsDestination.HOME,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.interests)) },
            icon = { Icon( Icons.Filled.List, null) },
            selected = currentRoute == NewsDestination.INTERESTS_ROUTE,
            onClick = { navigateToInterests(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Composable
fun NewsNavigationLogo(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_news_logo), 
            contentDescription = stringResource(id = R.string.news)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.news).uppercase(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black
        )
    }
}

@Preview
@Composable
fun PreviewAppDrawer(){
    NewsTheme {
        Surface {
            AppDrawer(
                currentRoute = NewsDestination.HOME,
                navigateToHome = { },
                navigateToInterests = { },
                closeDrawer = { }
            )
        }
    }
}