package com.base.androidappwithcompose.data.flow

import com.base.androidappwithcompose.model.ErrorModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T:Any> toResultFlow(call: suspend () -> Response<T>?) : Flow<ApiResult<T, ErrorModel>> {

    return flow {
        emit(ApiResult.Loading(null, true))
        val c = call()  /* have to initialize the call method first*/
        c?.let {
            try {
                if (c.isSuccessful && c.body() != null) {
                    c.body()?.let {
                        emit(ApiResult.Success(it))
                    }
                } else {
                    val errorBody = c.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, ErrorModel::class.java)
                    emit(ApiResult.Error(errorResponse))
                }
            }catch (_: Exception){
                emit(ApiResult.Error(null))
            }
        }
    }.flowOn(Dispatchers.IO)
}