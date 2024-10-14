package com.example.mypokedexcompose.data.berries

import kotlinx.serialization.Serializable

@Serializable
data class ItemBerry(
    val name: String,
    val url: String
)