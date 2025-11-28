package com.base.androidappwithcompose.app

import android.app.Application
import com.base.androidappwithcompose.data.local.kotpref.KotPref
import com.base.androidappwithcompose.data.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this

        Room().initRoom(this)
        KotPref().initialize(this)
    }

    companion object{
        private var app: BaseApp? = null
    }
}