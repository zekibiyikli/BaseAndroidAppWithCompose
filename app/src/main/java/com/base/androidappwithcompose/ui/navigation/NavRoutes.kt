package com.base.androidappwithcompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import com.base.androidappwithcompose.model.BottomNavModel

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Dashboard : NavRoutes("dashboard")
    object Notifications : NavRoutes("notifications")
    object NoInternet : NavRoutes("no_internet")
    object UserDetail : NavRoutes("user_detail/{userId}") {
        const val ARG_USER_ID = "userId"
        fun createRoute(userId: String) = "user_detail/$userId"
    }
}

val bottomItems = listOf(
    BottomNavModel(
        route = NavRoutes.Home.route,
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavModel(
        route = NavRoutes.Dashboard.route,
        title = "Dashboard",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    BottomNavModel(
        route = NavRoutes.Notifications.route,
        title = "Notifications",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    )
)
