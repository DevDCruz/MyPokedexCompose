package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class Generation(
    val name: String,
    val url: String
)