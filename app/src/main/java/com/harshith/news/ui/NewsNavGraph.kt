package com.harshith.news.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harshith.news.data.AppContainer
import com.harshith.news.model.NewsArticle
import com.harshith.news.ui.article.ArticleScreen
import com.harshith.news.ui.home.HomeRoute
import com.harshith.news.ui.interests.BookmarkedRoute
import kotlinx.serialization.json.Json.Default.decodeFromString

const val NEWS_ARTICLE = "newsArticle"
@Composable
fun NewsNavGraph(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit,
    navigationToDetail: (NewsArticle) -> Unit,
    startDestination: String = NewsDestination.HOME
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(route = NewsDestination.HOME){
            HomeRoute(
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
                navigateToArticleDetail = { navigationToDetail(it) }
            )
        }
        composable(NewsDestination.BOOKMARKED_ROUTE){
            BookmarkedRoute(
                isExpandedScreen = isExpandedScreen,
                onArticleClicked = { navigationToDetail(it) },
                openDrawer = openDrawer
            )
        }
        composable("${NewsDestination.ARTICLE_DETAILS_ROUTE}/{$NEWS_ARTICLE}", arguments = listOf(
            navArgument(NEWS_ARTICLE){
                type = NavType.StringType
            })
        ){
            ArticleScreen(
                newsArticle = decodeFromString<NewsArticle>
                    (it.arguments?.getString(NEWS_ARTICLE)?: ""),
                isBookmarked = false,
                onBack = { navController.popBackStack() }
            )
        }
    }
}