package com.harshith.news.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    modifier: Modifier,
    openNavigationDrawer: () -> Unit
){
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
    )
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "NEWS")
        },
        navigationIcon = {
            IconButton(onClick = { openNavigationDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation Menu Icon")
            }
        },
        colors = topAppBarColors)
}