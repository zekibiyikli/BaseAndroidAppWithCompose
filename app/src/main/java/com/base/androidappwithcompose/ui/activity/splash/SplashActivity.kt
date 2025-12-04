package com.base.androidappwithcompose.ui.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import com.base.androidappwithcompose.core.BaseActivity
import com.base.androidappwithcompose.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel>(SplashViewModel::class) {

    //lifecycles
    override fun initView() {
        setContent {
            SplashScreen()
        }

        viewModel.setDataStore(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    //Companion Object
    companion object{

    }
}