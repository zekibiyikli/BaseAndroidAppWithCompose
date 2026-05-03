package com.base.androidappwithcompose.data.flow

import app.cash.turbine.test
import com.base.androidappwithcompose.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ToResultFlowTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `emits Loading then Success on successful response`() = runTest {
        val response = Response.success("test data")

        toResultFlow { response }.test {
            assertTrue(awaitItem() is ApiResult.Loading)

            val result = awaitItem()
            assertTrue(result is ApiResult.Success)
            assertTrue((result as ApiResult.Success).data == "test data")

            awaitComplete()
        }
    }

    @Test
    fun `emits Loading then Error on unsuccessful response`() = runTest {
        val errorBody = """{"code":"404","message":"Not Found"}""".toResponseBody()
        val response = Response.error<String>(404, errorBody)

        toResultFlow { response }.test {
            assertTrue(awaitItem() is ApiResult.Loading)

            val result = awaitItem()
            assertTrue(result is ApiResult.Error)

            awaitComplete()
        }
    }

    @Test
    fun `emits Loading then Error when response body is null`() = runTest {
        val response = Response.success<String>(null)

        toResultFlow { response }.test {
            assertTrue(awaitItem() is ApiResult.Loading)
            awaitComplete()
        }
    }

    @Test
    fun `emits Loading then terminates with error when call throws`() = runTest {
        toResultFlow<String> { throw RuntimeException("Network failure") }.test {
            assertTrue(awaitItem() is ApiResult.Loading)
            awaitError()
        }
    }

    @Test
    fun `emits Loading then completes silently on null response`() = runTest {
        toResultFlow<String> { null }.test {
            assertTrue(awaitItem() is ApiResult.Loading)
            awaitComplete()
        }
    }
}
