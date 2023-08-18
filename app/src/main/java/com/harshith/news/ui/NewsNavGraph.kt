package com.harshith.news.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.harshith.news.NewsApplication.Companion.NEWS_APP_URI
import com.harshith.news.data.AppContainer
import com.harshith.news.ui.home.HomeRoute
import com.harshith.news.ui.home.HomeViewModel
import com.harshith.news.ui.interests.InterestsRoute
import com.harshith.news.ui.interests.InterestsViewModel

const val POST_ID = "postId"
@Composable
fun NewsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = NewsDestination.HOME
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(
            route = NewsDestination.HOME,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "$NEWS_APP_URI/${NewsDestination.HOME}?$POST_ID={$POST_ID}"
                }
            )
        ){navBackStackEntry ->
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    postRepository = appContainer.postRepository,
                    preSelectedPostId = navBackStackEntry.arguments?.getString(POST_ID)
                )
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(NewsDestination.INTERESTS_ROUTE){
            val interestsViewModel: InterestsViewModel = viewModel(
                factory = InterestsViewModel.provideFactory(
                    interestsRepository = appContainer.interestsRepository
                )
            )
            InterestsRoute(
                interestsViewModel = interestsViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}