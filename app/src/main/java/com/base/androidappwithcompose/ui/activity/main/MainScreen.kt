package com.base.androidappwithcompose.ui.activity.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.base.androidappwithcompose.ui.dialog.nointernet.NoInternetScreen
import com.base.androidappwithcompose.ui.navigation.MainNavHost
import com.base.androidappwithcompose.ui.navigation.bottomItems

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val showDialog by viewModel.showDialog.collectAsState()
    val isBottomBarVisible by viewModel.isBottomBarVisible.collectAsState()
    val navController = rememberNavController()

    NoInternetScreen(show = showDialog, onDismiss = { viewModel.closeDialog() })

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            MainNavHost(
                navController = navController,
                onShowBottomBar = { viewModel.showBottomBar() },
                onHideBottomBar = { viewModel.hideBottomBar() }
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
        bottomItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        imageVector = if (currentDestination == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) }
            )
        }
    }
}
