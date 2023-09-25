package com.harshith.news.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.harshith.news.data.AppContainer
import com.harshith.news.ui.theme.NewsTheme
import kotlinx.coroutines.launch

@Composable
fun NewsApp(
    appContainer: AppContainer,
    widthSizeClass: WindowWidthSizeClass
){
    NewsTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController){
            NewsNavigationActions(navController = navController)
        }
        val coroutineScope = rememberCoroutineScope()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route ?: NewsDestination.HOME
        val isExpandedSize = widthSizeClass == WindowWidthSizeClass.Expanded
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedSize)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToInterests = navigationActions.navigateToInterests,
                    closeDrawer = { coroutineScope.launch{sizeAwareDrawerState.close()} }
                )
            },
            drawerState = DrawerState(DrawerValue.Closed),
            gesturesEnabled = !isExpandedSize
        ) {
            NewsNavGraph(
                appContainer = appContainer,
                isExpandedScreen = isExpandedSize,
                navController = navController,
                openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                navigationToDetail = { navigationActions.navigateToArticleDetail(it) }
            )
        }
    }
}
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState{
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    return if(!isExpandedScreen){
        drawerState
    }else{
        DrawerState(DrawerValue.Closed)
    }
}

@Composable
fun rememberContentPaddingForScreen(
    additionalTop: Dp = 0.dp,
    excludeTop: Boolean = false
) = WindowInsets.systemBars
    .only(if(excludeTop) WindowInsetsSides.Bottom else WindowInsetsSides.Vertical)
    .add(WindowInsets(top = additionalTop))
    .asPaddingValues()