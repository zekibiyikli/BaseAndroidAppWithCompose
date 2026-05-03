package com.base.androidappwithcompose.data.server

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.model.response.UserResponse
import com.base.androidappwithcompose.util.UserPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRandomUsers(): Response<UserResponse> = apiService.getUsers(page = 1, results = 100)

    fun getPagedUsers(): Flow<PagingData<UserModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }
}
