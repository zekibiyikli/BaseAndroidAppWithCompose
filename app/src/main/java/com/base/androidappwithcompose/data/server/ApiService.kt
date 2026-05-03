package com.base.androidappwithcompose.data.server

import com.base.androidappwithcompose.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("${Config.API_PREFIX}/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Response<UserResponse>
}
