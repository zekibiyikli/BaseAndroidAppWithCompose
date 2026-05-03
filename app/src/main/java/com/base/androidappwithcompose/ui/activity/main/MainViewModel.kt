package com.base.androidappwithcompose.ui.activity.main

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.local.datastore.PreferencesKeys
import com.base.androidappwithcompose.data.local.datastore.readFromDataStore
import com.base.androidappwithcompose.data.local.datastore.saveToDataStore
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    val repo: MainRepo,
    val localData: LocalSharedPreferences
) : BaseViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _isBottomBarVisible = MutableStateFlow(true)
    val isBottomBarVisible: StateFlow<Boolean> = _isBottomBarVisible.asStateFlow()

    fun openDialog() { _showDialog.value = true }
    fun closeDialog() { _showDialog.value = false }

    fun showBottomBar() { _isBottomBarVisible.value = true }
    fun hideBottomBar() { _isBottomBarVisible.value = false }

    fun initDataStore(context: Context) {
        viewModelScope.launch {
            context.saveToDataStore(PreferencesKeys.USER_NAME, "Test")
            context.saveToDataStore(PreferencesKeys.USER_AGE, 10)
        }
    }

    fun readDataStore(context: Context) {
        val nameFlow: Flow<String> = context.readFromDataStore(PreferencesKeys.USER_NAME, "")
        val ageFlow: Flow<Int> = context.readFromDataStore(PreferencesKeys.USER_AGE, 0)
        viewModelScope.launch {
            nameFlow.collect { Timber.d("$it") }
        }
        viewModelScope.launch {
            ageFlow.collect { Timber.d("$it") }
        }
    }
}
