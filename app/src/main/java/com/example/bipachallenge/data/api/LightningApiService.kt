package com.example.bipachallenge.data.api

import com.example.bipachallenge.data.dto.NodeRankingResponse
import retrofit2.http.GET

interface LightningApiService {

    @GET("api/v1/lightning/nodes/rankings/connectivity")
    suspend fun getNodeRankings(): List<NodeRankingResponse>
}
