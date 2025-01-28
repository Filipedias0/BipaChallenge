package com.example.bipachallenge.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Language(val language: String) {
    @SerialName("en") EN("en"),
    @SerialName("pt-BR") PT_BR("pt-BR");
}