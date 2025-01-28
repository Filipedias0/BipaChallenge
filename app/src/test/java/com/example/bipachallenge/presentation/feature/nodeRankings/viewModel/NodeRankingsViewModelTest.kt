package com.example.bipachallenge.presentation.feature.nodeRankings.viewModel

import com.example.bipachallenge.domain.model.NodeRankingData
import com.example.bipachallenge.domain.useCase.GetNodeRankingsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NodeRankingsViewModelTest {

    private lateinit var viewModel: NodeRankingsViewModel
    private lateinit var getNodeRankingsUseCase: GetNodeRankingsUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())

        getNodeRankingsUseCase = mockk()
        viewModel = NodeRankingsViewModel(getNodeRankingsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()

        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getNodeRankings success`() = runTest {
        val mockData = mockk<List<NodeRankingData>>(relaxed = true)

        coEvery { getNodeRankingsUseCase.invoke() } returns mockData

        viewModel.getNodeRankings()
        advanceUntilIdle()

        val nodeRankings = viewModel.nodeRankings.first()

        assertEquals(mockData, nodeRankings)
        assertFalse(viewModel.isLoading.value)
        assertNull(viewModel.errorMessage.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getNodeRankings failure`() = runTest {
        val errorMessage = "API error"
        coEvery { getNodeRankingsUseCase.invoke() } throws Exception(errorMessage)

        viewModel.getNodeRankings()

        advanceUntilIdle()

        val nodeRankings = viewModel.nodeRankings.first()

        assertTrue(nodeRankings.isEmpty())
        assertFalse(viewModel.isLoading.value)
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    @Test
    fun `test clearErrorMessage works correctly`() {
        viewModel.clearErrorMessage()

        assertNull(viewModel.errorMessage.value)
    }
}
