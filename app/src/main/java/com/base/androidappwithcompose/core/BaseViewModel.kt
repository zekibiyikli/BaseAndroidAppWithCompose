package com.base.androidappwithcompose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.androidappwithcompose.model.ErrorModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel() {

    private val _showProgress = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> = _showProgress.asStateFlow()

    private val _generalError = MutableStateFlow<ErrorModel?>(null)
    val generalError: StateFlow<ErrorModel?> = _generalError.asStateFlow()

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is HttpException) {
            _generalError.value = ErrorModel("${throwable.code()}", throwable.message())
        }
    }

    fun sendRequest(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _showProgress.value = true
            try {
                block()
            } finally {
                _showProgress.value = false
            }
        }
    }

    fun sendRequestWithError(
        block: suspend CoroutineScope.() -> Unit,
        errorHandler: suspend CoroutineScope.(ErrorModel?) -> Unit
    ) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _showProgress.value = true
            try {
                block()
            } catch (e: HttpException) {
                val errorResult = e.response()?.errorBody()?.string()
                val obj: ErrorModel? = gson.fromJson(errorResult, ErrorModel::class.java)
                errorHandler(obj)
            } finally {
                _showProgress.value = false
            }
        }
    }

    fun sendRequestWithoutLoading(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }

    companion object {
        val gson = Gson()
    }
}
