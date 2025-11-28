package com.base.androidappwithcompose.ui.fragment.userdetail

import com.base.androidappwithcompose.core.BaseRepository
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    val repo: MainRepo
) : BaseViewModel<BaseRepository>(){

}
