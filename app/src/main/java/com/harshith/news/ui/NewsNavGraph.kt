package com.harshith.news.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.harshith.news.NewsApplication.Companion.NEWS_APP_URI
import com.harshith.news.data.AppContainer
import com.harshith.news.ui.article.ArticleScreen
import com.harshith.news.ui.article.ArticleViewModel
import com.harshith.news.ui.home.HomeRoute
import com.harshith.news.ui.interests.InterestsRoute
import com.harshith.news.ui.interests.InterestsViewModel

const val POST_ID = "postId"
@Composable
fun NewsNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
    navigationToDetail: (String) -> Unit,
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
            HomeRoute(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
                navigateToArticleDetail = { navigationToDetail(it) }
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
        composable("${NewsDestination.ARTICLE_DETAILS_ROUTE}/{$POST_ID}"){ navBackStackEntry ->
            ArticleScreen(
                isExpandedScreen = isExpandedScreen,
                onBack = { navController.popBackStack() },
                isFavourite = true,
                onToggleFavourite = { },
                //navigationArgs = navBackStackEntry.arguments?.getString(POST_ID),
            )
        }
    }
}