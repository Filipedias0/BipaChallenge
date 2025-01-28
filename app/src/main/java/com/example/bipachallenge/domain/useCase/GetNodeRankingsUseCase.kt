package com.example.bipachallenge.domain.useCase

import com.example.bipachallenge.data.repository.LightningRepository
import com.example.bipachallenge.domain.model.NodeRankingData

class GetNodeRankingsUseCase(private val lightningRepository: LightningRepository) {

    suspend fun invoke(): List<NodeRankingData> {
        return lightningRepository.getNodeRankings()
    }
}
