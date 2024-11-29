package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val language: Language,
    val name: String
)