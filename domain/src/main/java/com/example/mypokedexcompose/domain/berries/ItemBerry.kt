package com.example.mypokedexcompose.domain.berries

import kotlinx.serialization.Serializable

@Serializable
data class ItemBerry(
    val name: String,
    val url: String
)