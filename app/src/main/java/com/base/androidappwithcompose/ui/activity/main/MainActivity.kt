package com.base.androidappwithcompose.ui.activity.main

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.base.androidappwithcompose.core.BaseActivity
import com.base.androidappwithcompose.ui.activity.LocalMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class
) {

    var isBottomBarVisible by mutableStateOf(true)

    //lifecycles
    override fun initView() {
        setContent {
            CompositionLocalProvider(
                LocalMainActivity provides this
            ) {
                MainScreen()
            }
        }
        viewModel.readDataStore(this)
    }

    fun showBottomBar() {
        isBottomBarVisible = true
    }

    fun hideBottomBar() {
        isBottomBarVisible = false
    }


    //Companion Object
    companion object{

    }
}