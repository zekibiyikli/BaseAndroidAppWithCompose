package com.base.androidappwithcompose.ui.activity

import androidx.compose.runtime.staticCompositionLocalOf
import com.base.androidappwithcompose.ui.activity.main.MainActivity

val LocalMainActivity = staticCompositionLocalOf<MainActivity> {
    error("MainActivity not provided")
}
