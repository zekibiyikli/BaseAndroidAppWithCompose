package com.base.androidappwithcompose.data.server

import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class UserTokenHeader @Inject constructor(
    private val localData: LocalSharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localData.userToken
        val request = chain.request().newBuilder()
            .addHeader(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE)
            .apply { if (token.isNotBlank()) header("Authorization", "Bearer $token") }
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_CONTENT_TYPE_KEY = "Content-Type"
        private const val HEADER_CONTENT_TYPE_VALUE = "application/json"
    }
}
