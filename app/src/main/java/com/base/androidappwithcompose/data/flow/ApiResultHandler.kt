package com.base.androidappwithcompose.data.flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ApiResultHandler<T, E>(
    private val onSuccess: (T?) -> Unit,
    private val onFailure: (E?) -> Unit
) {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun handleApiResult(result: ApiResult<T?, E?>) {
        when (result.status) {
            ApiStatus.LOADING -> _loading.value = true
            ApiStatus.SUCCESS -> {
                _loading.value = false
                onSuccess(result.data)
            }
            ApiStatus.ERROR -> {
                _loading.value = false
                onFailure(result.message)
            }
        }
    }
}
