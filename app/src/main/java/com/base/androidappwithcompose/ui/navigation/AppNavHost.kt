package com.base.androidappwithcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.base.androidappwithcompose.ui.fragment.dashboard.DashboardScreen
import com.base.androidappwithcompose.ui.fragment.home.HomeScreen
import com.base.androidappwithcompose.ui.fragment.notifications.NotificationsScreen
import com.base.androidappwithcompose.ui.fragment.userdetail.UserDetailScreen

@Composable
fun MainNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController = navController)
        }

        composable(Routes.NOTIFICATIONS) {
            NotificationsScreen(navController = navController)
        }

        composable(
            route = Routes.USER_DETAIL,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            UserDetailScreen(userId = userId, navController = navController)
        }
    }
}