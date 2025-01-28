package com.example.bipachallenge.data.dto

data class NodeRankingResponse(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: Long,
    val firstSeen: Long,
    val updatedAt: Long,
    val city: Map<String, String>?,
    val country: Map<String, String>?
) {
    fun getCityInSelectedLanguages(): Map<String, String>? {
        return city?.filterKeys { it == Language.PT_BR.language || it == Language.EN.language }
    }

    fun getCountryInSelectedLanguages(): Map<String, String>? {
        return country?.filterKeys { it == Language.PT_BR.language || it == Language.EN.language }
    }
}
