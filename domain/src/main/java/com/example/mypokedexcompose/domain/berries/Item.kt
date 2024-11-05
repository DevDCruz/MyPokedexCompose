package com.example.mypokedexcompose.domain.berries

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val url: String
)