package com.base.androidappwithcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.base.androidappwithcompose.ui.dialog.nointernet.NoInternetScreen
import com.base.androidappwithcompose.ui.fragment.dashboard.DashboardScreen
import com.base.androidappwithcompose.ui.fragment.home.HomeScreen
import com.base.androidappwithcompose.ui.fragment.notifications.NotificationsScreen
import com.base.androidappwithcompose.ui.fragment.userdetail.UserDetailScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    onShowBottomBar: () -> Unit,
    onHideBottomBar: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController, onShowBottomBar = onShowBottomBar)
        }

        composable(NavRoutes.Dashboard.route) {
            DashboardScreen(navController = navController, onShowBottomBar = onShowBottomBar)
        }

        composable(NavRoutes.Notifications.route) {
            NotificationsScreen(navController = navController, onShowBottomBar = onShowBottomBar)
        }

        composable(
            route = NavRoutes.UserDetail.route,
            arguments = listOf(navArgument(NavRoutes.UserDetail.ARG_USER_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString(NavRoutes.UserDetail.ARG_USER_ID) ?: ""
            UserDetailScreen(userId = userId, navController = navController, onHideBottomBar = onHideBottomBar)
        }

        composable(NavRoutes.NoInternet.route) {
            NoInternetScreen(false, {})
        }
    }
}
