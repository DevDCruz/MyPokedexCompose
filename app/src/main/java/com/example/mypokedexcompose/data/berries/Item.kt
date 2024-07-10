package com.example.mypokedexcompose.data.berries

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val url: String
)