package com.example.bipachallenge.domain.model

data class NodeRankingData(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: String,
    val firstSeen: String,
    val updatedAt: String,
    val city: Map<String, String>?,
    val country: Map<String, String>?
)