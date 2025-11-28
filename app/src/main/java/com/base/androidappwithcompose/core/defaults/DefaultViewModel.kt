package com.base.androidappwithcompose.core.defaults

import com.base.androidappwithcompose.core.BaseRepository
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class DefaultViewModel @Inject constructor(
    val repo: MainRepo,
    val localData: LocalSharedPreferences
) : BaseViewModel<BaseRepository>()
