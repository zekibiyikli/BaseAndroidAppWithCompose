package com.base.androidappwithcompose.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.base.androidappwithcompose.data.server.ApiService
import com.base.androidappwithcompose.model.UserModel

class UserPagingSource(
    private val api: ApiService
) : PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val page = params.key ?: 1
            val response = api.getRandomUsers2()
            val users = response.results ?: emptyList() // UserResponse.results: List<UserModel>

            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
