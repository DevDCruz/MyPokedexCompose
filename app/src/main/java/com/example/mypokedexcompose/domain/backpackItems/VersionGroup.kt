package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(
    val name: String,
    val url: String
)