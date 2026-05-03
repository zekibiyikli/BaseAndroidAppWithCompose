package com.base.androidappwithcompose.data.flow

import com.base.androidappwithcompose.model.ErrorModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiResultTest {

    @Test
    fun `Success has SUCCESS status with correct data and null message`() {
        val result = ApiResult.Success("hello")

        assertEquals(ApiStatus.SUCCESS, result.status)
        assertEquals("hello", result.data)
        assertNull(result.message)
    }

    @Test
    fun `Success with null data is allowed`() {
        val result = ApiResult.Success<String>(null)

        assertEquals(ApiStatus.SUCCESS, result.status)
        assertNull(result.data)
    }

    @Test
    fun `Error has ERROR status with correct exception and null data`() {
        val errorModel = ErrorModel("404", "Not Found")
        val result = ApiResult.Error(errorModel)

        assertEquals(ApiStatus.ERROR, result.status)
        assertNull(result.data)
        assertEquals(errorModel, result.message)
    }

    @Test
    fun `Error with null exception is allowed`() {
        val result = ApiResult.Error<ErrorModel>(null)

        assertEquals(ApiStatus.ERROR, result.status)
        assertNull(result.message)
    }

    @Test
    fun `Loading has LOADING status with correct isLoading flag`() {
        val result = ApiResult.Loading<String>(null, isLoading = true)

        assertEquals(ApiStatus.LOADING, result.status)
        assertTrue(result.isLoading)
        assertNull(result.data)
    }

    @Test
    fun `ApiResult subtypes are correctly identified with smart cast`() {
        val results: List<ApiResult<String, ErrorModel>> = listOf(
            ApiResult.Success("data"),
            ApiResult.Error(ErrorModel("500", "Error")),
            ApiResult.Loading(null, true)
        )

        assertTrue(results[0] is ApiResult.Success)
        assertTrue(results[1] is ApiResult.Error)
        assertTrue(results[2] is ApiResult.Loading)
    }
}
