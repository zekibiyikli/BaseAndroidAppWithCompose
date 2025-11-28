package com.base.androidappwithcompose.data.server

import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class UserTokenHeader @Inject constructor(
    private val localData: LocalSharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token=localData.userToken
        val newRequest = originalRequest.newBuilder()
            .addHeader(KEY_CONTENT_TYPE, HEADER_CONTENT_TYPE)
            .header("authorization", Config.HEADER_TOKEN) // Header ekle
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val HEADER_CONTENT_TYPE = "application/json"
        const val KEY_CONTENT_TYPE = "content-type"
    }
}
