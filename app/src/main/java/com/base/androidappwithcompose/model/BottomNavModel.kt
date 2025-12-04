package com.base.androidappwithcompose.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavModel(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
