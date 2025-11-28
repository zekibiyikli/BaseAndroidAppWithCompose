package com.base.androidappwithcompose.ui.activity.splash

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.base.androidappwithcompose.core.BaseRepository
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.local.datastore.PreferencesKeys
import com.base.androidappwithcompose.data.local.datastore.saveToDataStore
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val repo: MainRepo
) : BaseViewModel<BaseRepository>(){

    fun setDataStore(context: Context){
        viewModelScope.launch {
            context.saveToDataStore(PreferencesKeys.USER_NAME,"Test")
            context.saveToDataStore(PreferencesKeys.USER_AGE,10)
        }
    }
}
