package com.example.bipachallenge.domain.useCase

import com.example.bipachallenge.data.repository.LightningRepository
import com.example.bipachallenge.domain.model.NodeRankingData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class GetNodeRankingsUseCaseTest {

    private lateinit var lightningRepository: LightningRepository
    private lateinit var getNodeRankingsUseCase: GetNodeRankingsUseCase

    @Before
    fun setUp() {
        lightningRepository = mockk()
        getNodeRankingsUseCase = GetNodeRankingsUseCase(lightningRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test execute returns node rankings correctly`() = runBlocking {
        val mockData = mockk<List<NodeRankingData>>()

        coEvery { lightningRepository.getNodeRankings() } returns mockData

        val result = getNodeRankingsUseCase.invoke()

        assertEquals(
            mockData,
            result
        )
    }

    @Test(expected = Exception::class)
    fun `test execute throws exception when repository fails`(): Unit = runBlocking {
        coEvery { lightningRepository.getNodeRankings() } throws Exception("API error")

        getNodeRankingsUseCase.invoke()
    }
}
