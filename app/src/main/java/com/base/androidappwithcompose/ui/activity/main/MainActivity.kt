package com.base.androidappwithcompose.ui.activity.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.base.androidappwithcompose.core.BaseActivity
import com.base.androidappwithcompose.enums.NetworkStatus
import com.base.androidappwithcompose.util.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {

    private lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        viewModel.initDataStore(this)
        setContent { MainScreen() }

        networkMonitor = NetworkMonitor(this) { status ->
            when (status) {
                NetworkStatus.Available -> viewModel.closeDialog()
                NetworkStatus.Lost -> viewModel.openDialog()
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
}
