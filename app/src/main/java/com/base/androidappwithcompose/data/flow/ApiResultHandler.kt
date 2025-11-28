package com.base.androidappwithcompose.data.flow

import androidx.lifecycle.MutableLiveData

class ApiResultHandler<T,E>(private val onSuccess: (T?) -> Unit, private val onFailure: (E?) -> Unit)  {

    var loading = MutableLiveData<Boolean>()

    fun handleApiResult(result: ApiResult<T?, E?>) {
        when (result.status) {
            ApiStatus.LOADING -> {
                loading.value = true
                //onLoading(result.data)
            }

            ApiStatus.SUCCESS -> {
                loading.value = false
                onSuccess(result.data)
            }

            ApiStatus.ERROR -> {
                loading.value = false
                onFailure(result.message)
            }
        }
    }
}