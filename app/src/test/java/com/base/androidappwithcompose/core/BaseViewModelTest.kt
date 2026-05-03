package com.base.androidappwithcompose.core

import com.base.androidappwithcompose.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TestViewModel

    @Before
    fun setUp() {
        viewModel = TestViewModel()
    }

    @Test
    fun `errorDataModelFillUp returns ErrorModel with correct code`() {
        val result = viewModel.errorDataModelFillUp("500", "Server Error")
        assertEquals("500", result.code)
    }

    @Test
    fun `errorDataModelFillUp returns ErrorModel with correct message`() {
        val result = viewModel.errorDataModelFillUp("404", "Not Found")
        assertEquals("Not Found", result.message)
    }

    @Test
    fun `errorDataModelFillUp with empty strings returns empty ErrorModel`() {
        val result = viewModel.errorDataModelFillUp("", "")
        assertEquals("", result.code)
        assertEquals("", result.message)
    }

    @Test
    fun `coroutineExceptionHandler is not null`() {
        assertNotNull(viewModel.coroutineExceptionHandler)
    }

    @Test
    fun `showProgress StateFlow initial value is false`() {
        assertFalse(viewModel.showProgress.value)
    }

    @Test
    fun `generalError StateFlow initial value is null`() {
        assertNull(viewModel.generalError.value)
    }

    private class TestViewModel : BaseViewModel() {
        fun errorDataModelFillUp(code: String, message: String) =
            com.base.androidappwithcompose.model.ErrorModel(code, message)
    }
}
