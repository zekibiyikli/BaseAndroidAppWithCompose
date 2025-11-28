package com.base.androidappwithcompose.data.flow

sealed class ApiResult <out T,out E>(val status: ApiStatus, val data: T?, val message:E?) {

    data class Success<out R>(val _data: R?): ApiResult<R,Nothing>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error<out E>(val exception:E?): ApiResult<Nothing,E>(
        status = ApiStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out R>(val _data: R?, val isLoading: Boolean): ApiResult<R,Nothing>(
        status = ApiStatus.LOADING,
        data = _data,
        message = null
    )

}