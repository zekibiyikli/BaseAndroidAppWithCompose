package com.base.androidappwithcompose.ui.activity.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.base.androidappwithcompose.core.BaseRepository
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.local.datastore.PreferencesKeys
import com.base.androidappwithcompose.data.local.datastore.readFromDataStore
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val repo: MainRepo,
    val localData: LocalSharedPreferences
) : BaseViewModel<BaseRepository>(){

    fun readDataStore(context: Context){
        val nameFlow: Flow<String> = context.readFromDataStore(PreferencesKeys.USER_NAME, "")
        val ageFlow: Flow<Int> = context.readFromDataStore(PreferencesKeys.USER_AGE, 0)
        viewModelScope.launch {
            nameFlow.collect {
                Log.e("DevTest","$it")
            }
        }
        viewModelScope.launch {
            ageFlow.collect {
                Log.e("DevTest","$it")
            }
        }
    }
}