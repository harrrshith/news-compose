package com.harshith.news.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object NewsDestination{
    const val HOME = "home"
    const val INTERESTS_ROUTE = "interests"
    const val  ARTICLE_DETAILS_ROUTE = "articleDetails"
}

class NewsNavigationActions(navController: NavHostController){
    val navigateToHome : () -> Unit = {
        navController.navigate(NewsDestination.HOME){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true

        }
    }

    val navigateToInterests: () -> Unit = {
        navController.navigate(NewsDestination.INTERESTS_ROUTE){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToArticleDetail: (String) -> Unit = {id ->
        navController.navigate("${NewsDestination.ARTICLE_DETAILS_ROUTE}/$id"){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}