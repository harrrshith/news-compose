package com.harshith.news.ui.interests

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun InterestsRoute(
    interestsViewModel: InterestsViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember {
        SnackbarHostState()
    }
){
    val tabContent = rememberTabContent(interestsViewModel = interestsViewModel)
    val (currentSection, updataSection) = rememberSaveable{
        mutableStateOf(tabContent.first().section)
    }
    InterestsScreen(
        tabContent = tabContent,
        currentSection = currentSection,
        isExpandedScreen = isExpandedScreen,
        onTabChange = updataSection,
        openDrawer = { openDrawer() },
        snackbarHostState = snackbarHostState
    )

}