package com.base.androidappwithcompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import com.base.androidappwithcompose.model.BottomNavModel
import kotlin.text.replaceFirstChar

object Routes {
    const val HOME = "home"
    const val DASHBOARD = "dashboard"
    const val NOTIFICATIONS = "notifications"
    const val USER_DETAIL = "user_detail/{userId}"
    const val NO_INTERNET = "no_internet"
}

val bottomItems = listOf(
    BottomNavModel(
        route = Routes.HOME,
        title = Routes.HOME.replaceFirstChar { it.uppercase() },
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavModel(
        route = Routes.DASHBOARD,
        title = Routes.DASHBOARD.replaceFirstChar { it.uppercase() },
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite
    ),
    BottomNavModel(
        route = Routes.NOTIFICATIONS,
        title = Routes.NOTIFICATIONS.replaceFirstChar { it.uppercase() },
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    )
)
