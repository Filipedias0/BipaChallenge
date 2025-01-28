package com.example.bipachallenge.data.repository

import android.util.Log
import com.example.bipachallenge.data.api.LightningApiService
import com.example.bipachallenge.data.dto.NodeRankingResponse
import com.example.bipachallenge.domain.model.NodeRankingData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LightningRepository(private val service: LightningApiService) {
    suspend fun getNodeRankings(): List<NodeRankingData> {
        return try {
            service.getNodeRankings().toNodeRankingDataList()
        }catch (e: Exception){
            Log.d("errorTeste", e.message.toString())
            service.getNodeRankings().toNodeRankingDataList()
        }
    }

    private fun List<NodeRankingResponse>.toNodeRankingDataList(): List<NodeRankingData> {
        return this.map { it.toNodeRankingData() }
    }

    private fun NodeRankingResponse.toNodeRankingData(): NodeRankingData {
        val filteredCity: Map<String, String>? = getCityInSelectedLanguages()
        val filteredCountry: Map<String, String>? = getCountryInSelectedLanguages()
        val result = capacity / 100_000_000L.toDouble()

        return NodeRankingData(
            publicKey = this.publicKey,
            alias = this.alias,
            channels = this.channels,
            capacity =  "%.8f".format(result),
            firstSeen = formatUnixTime(firstSeen),
            updatedAt = formatUnixTime(updatedAt),
            city = filteredCity,
            country = filteredCountry
        )
    }

    private fun formatUnixTime(unixTime: Long): String {
        val date = Date(unixTime * 1000)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }
}
