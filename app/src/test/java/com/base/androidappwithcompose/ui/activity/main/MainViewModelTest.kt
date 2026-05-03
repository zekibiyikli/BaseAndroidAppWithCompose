package com.base.androidappwithcompose.ui.activity.main

import com.base.androidappwithcompose.data.local.lsp.LocalSharedPreferences
import com.base.androidappwithcompose.data.server.MainRepo
import com.base.androidappwithcompose.util.MainDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repo: MainRepo = mockk(relaxed = true)
    private val localData: LocalSharedPreferences = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(repo, localData)
    }

    @Test
    fun `showDialog is false initially`() {
        assertFalse(viewModel.showDialog.value)
    }

    @Test
    fun `openDialog sets showDialog to true`() {
        viewModel.openDialog()
        assertTrue(viewModel.showDialog.value)
    }

    @Test
    fun `closeDialog sets showDialog to false`() {
        viewModel.openDialog()
        viewModel.closeDialog()
        assertFalse(viewModel.showDialog.value)
    }

    @Test
    fun `openDialog then closeDialog repeatedly works correctly`() {
        repeat(3) {
            viewModel.openDialog()
            assertTrue(viewModel.showDialog.value)
            viewModel.closeDialog()
            assertFalse(viewModel.showDialog.value)
        }
    }

    @Test
    fun `isBottomBarVisible is true initially`() {
        assertTrue(viewModel.isBottomBarVisible.value)
    }

    @Test
    fun `hideBottomBar sets isBottomBarVisible to false`() {
        viewModel.hideBottomBar()
        assertFalse(viewModel.isBottomBarVisible.value)
    }

    @Test
    fun `showBottomBar sets isBottomBarVisible back to true`() {
        viewModel.hideBottomBar()
        viewModel.showBottomBar()
        assertTrue(viewModel.isBottomBarVisible.value)
    }
}
