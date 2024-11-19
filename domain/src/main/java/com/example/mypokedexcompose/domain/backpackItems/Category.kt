package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val url: String
)