package com.base.androidappwithcompose.ui.activity.main

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.base.androidappwithcompose.core.BaseActivity
import com.base.androidappwithcompose.enums.NetworkStatus
import com.base.androidappwithcompose.ui.activity.LocalMainActivity
import com.base.androidappwithcompose.ui.dialog.nointernet.NoInternetScreen
import com.base.androidappwithcompose.util.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class
) {

    var isBottomBarVisible by mutableStateOf(true)
    private lateinit var networkMonitor: NetworkMonitor

    //lifecycles
    override fun initView() {
        setContent {
            CompositionLocalProvider(
                LocalMainActivity provides this
            ) {
                MainScreen()
            }
        }

        networkMonitor = NetworkMonitor(this) { status ->
            when (status) {
                NetworkStatus.Available -> {
                    runOnUiThread {
                        //Internet Connected
                        viewModel.closeDialog()
                    }
                }
                NetworkStatus.Lost -> {
                    runOnUiThread {
                        //Internet Disconnected
                        viewModel.openDialog()
                    }
                }
            }
        }

        viewModel.readDataStore(this)
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onPause() {
        super.onPause()
        networkMonitor.unregister()
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