package com.base.androidappwithcompose.data.server

import com.base.androidappwithcompose.model.response.UserResponse
import com.base.androidappwithcompose.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MainRepoTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val apiService: ApiService = mockk()
    private val repo = MainRepo(apiService)

    @Test
    fun `getRandomUsers delegates call to apiService`() = runTest {
        val fakeResponse = Response.success(UserResponse(results = null, info = null))
        coEvery { apiService.getUsers(page = 1, results = 100) } returns fakeResponse

        val result = repo.getRandomUsers()

        coVerify(exactly = 1) { apiService.getUsers(page = 1, results = 100) }
        assertEquals(fakeResponse, result)
    }

    @Test
    fun `getRandomUsers returns successful response`() = runTest {
        val userResponse = UserResponse(results = null, info = null)
        coEvery { apiService.getUsers(page = 1, results = 100) } returns Response.success(userResponse)

        val result = repo.getRandomUsers()

        assertTrue(result.isSuccessful)
        assertEquals(userResponse, result.body())
    }

    @Test
    fun `getRandomUsers returns error response`() = runTest {
        val errorBody = "".toResponseBody()
        coEvery { apiService.getUsers(page = 1, results = 100) } returns Response.error(500, errorBody)

        val result = repo.getRandomUsers()

        assertTrue(!result.isSuccessful)
        assertEquals(500, result.code())
    }
}
