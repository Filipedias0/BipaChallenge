package com.example.bipachallenge.data.repository

import com.example.bipachallenge.data.api.LightningApiService
import com.example.bipachallenge.data.dto.NodeRankingResponse
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class LightningRepositoryTest {

    private lateinit var apiService: LightningApiService
    private lateinit var repository: LightningRepository

    @Before
    fun setUp() {
        apiService = mockk()
        repository = LightningRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getNodeRankings returns data correctly`() = runTest {
        val fakeNodeRankingResponse = listOf(
            NodeRankingResponse(
                publicKey = "abc123xyz456",
                alias = "Fake Node",
                channels = 5,
                capacity = 1000000L,
                firstSeen = 1622540100000L,
                updatedAt = 1622540200000L,
                city = mapOf("en" to "Fake City", "pt_br" to "Cidade Falsa"),
                country = mapOf("en" to "Fake Country", "pt_br" to "País Falso")
            )
        )

        coEvery { apiService.getNodeRankings() } returns fakeNodeRankingResponse

        val result = repository.getNodeRankings()

        advanceUntilIdle()

        assertEquals(fakeNodeRankingResponse[0].alias, result[0].alias)
    }

    @Test(expected = Exception::class)
    fun `test getNodeRankings throws exception`(): Unit = runTest {
        coEvery { apiService.getNodeRankings() } throws Exception("API error")

        repository.getNodeRankings()
    }
}
