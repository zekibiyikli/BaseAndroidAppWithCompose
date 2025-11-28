package com.base.androidappwithcompose.ui.activity.main

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.core.BaseActivity
import com.base.androidappwithcompose.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class
) {

    //Variables
    override val getLayoutId: Int
        get() = R.layout.activity_main

    var navView: BottomNavigationView?=null

    //lifecycles
    override fun initView() {
        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView?.setupWithNavController(navController)

        viewModel.readDataStore(this)
    }

    fun showHideBottomNavigation(isShow:Boolean){
        if (isShow){
            navView?.let {
                it.animate().translationY(0f)
            }
        }else{
            navView?.let {
                it.animate().translationY(it.height.toFloat())
            }
        }
    }

    //Companion Object
    companion object{

    }
}