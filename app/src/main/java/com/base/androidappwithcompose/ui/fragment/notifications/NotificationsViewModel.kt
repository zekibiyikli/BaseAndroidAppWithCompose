package com.base.androidappwithcompose.ui.fragment.notifications

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.server.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    val repo: MainRepo
) : BaseViewModel() {

    val userFlow = repo.getPagedUsers().cachedIn(viewModelScope)
}
