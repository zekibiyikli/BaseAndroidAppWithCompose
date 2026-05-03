package com.base.androidappwithcompose.ui.fragment.home

import com.base.androidappwithcompose.core.BaseViewModel
import com.base.androidappwithcompose.data.flow.ApiResult
import com.base.androidappwithcompose.data.flow.toResultFlow
import com.base.androidappwithcompose.data.server.MainRepo
import com.base.androidappwithcompose.model.ErrorModel
import com.base.androidappwithcompose.model.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repo: MainRepo
) : BaseViewModel() {

    init {
        getRandomUsers()
    }

    private val _usersFlow = MutableStateFlow<ApiResult<UserResponse, ErrorModel>?>(null)
    val usersFlow = _usersFlow.asStateFlow()
    fun getRandomUsers(){
        sendRequest {
            toResultFlow {
                repo.getRandomUsers()
            }.collect{result->
                _usersFlow.value = result
            }
        }
    }

}