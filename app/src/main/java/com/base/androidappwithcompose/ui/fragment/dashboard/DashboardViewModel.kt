package com.base.androidappwithcompose.ui.fragment.dashboard

import com.base.androidappwithcompose.core.BaseRepository
import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.flow.ApiResult
import com.base.androidappwithcompose.data.flow.toResultFlow
import com.base.androidappwithcompose.data.server.MainRepo
import com.base.androidappwithcompose.model.ErrorModel
import com.base.androidappwithcompose.model.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val repo: MainRepo
) : BaseViewModel<BaseRepository>(){

    private val _usersFlow = MutableSharedFlow<ApiResult<UserResponse, ErrorModel>?>(replay = 0)
    val usersFlow = _usersFlow.asSharedFlow()
    fun getRandomUsers(){
        sendRequest {
            toResultFlow {
                repo.getRandomUsers()
            }.collect{result->
                _usersFlow.emit(result)
            }
        }
    }

}
