package com.example.bipachallenge.data.repository

import com.example.bipachallenge.domain.model.NodeRankingData

interface LightningRepository {
    suspend fun getNodeRankings(): List<NodeRankingData>
}
