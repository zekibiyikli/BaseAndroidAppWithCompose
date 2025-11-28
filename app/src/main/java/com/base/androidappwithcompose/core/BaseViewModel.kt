package com.base.androidappwithcompose.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.androidappwithcompose.model.ErrorModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<R : BaseRepository>() : ViewModel(), CoroutineScope {

    //Variables
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()
    val generalError: MutableLiveData<ErrorModel> = MutableLiveData()
    private val job= Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    //Observers
    val coroutineExceptionHandler: CoroutineExceptionHandler? = try {
        CoroutineExceptionHandler { _, throwable ->
            if (throwable is HttpException) {

            }
        }
    } catch (e: Exception) {
        null
    }

    //Functions
    inline fun sendRequest(
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        if (coroutineExceptionHandler != null) {
            viewModelScope.launch(coroutineExceptionHandler) {
                showProgress.postValue(true)
                try {
                    block()
                } finally {
                    showProgress.postValue(false)
                }
            }
        } else {
            val errorModel = errorDataModelFillUp("Error", "")
            generalError.postValue(errorModel)
        }
    }

    inline fun sendRequestWithError(
        crossinline block: suspend CoroutineScope.() -> Unit,
        crossinline errorHandler: suspend CoroutineScope.(ErrorModel?) -> Unit
    ) {
        viewModelScope.launch() {
            showProgress.postValue(true)
            try {
                block()
            }catch (e:HttpException){
                val errorResult =e.response()?.errorBody()?.string()
                val type = object : TypeToken<ErrorModel>() {}.rawType
                val obj: ErrorModel? = Gson().fromJson<ErrorModel>(errorResult, type)
                errorHandler(obj)
            } finally {
                showProgress.postValue(false)
            }
        }
    }

    inline fun sendRequestWithoutLoading(
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        if (coroutineExceptionHandler != null) {
            viewModelScope.launch(coroutineExceptionHandler) {
                try {
                    block()
                } finally {

                }
            }
        } else {
            val errorModel = errorDataModelFillUp("Error", "")
            generalError.postValue(errorModel)
        }
    }


    fun errorDataModelFillUp(code:String,message: String): ErrorModel {
        return ErrorModel(code,message)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}