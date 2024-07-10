package com.example.mypokedexcompose.data.items

import kotlinx.serialization.Serializable

@Serializable
data class Attribute(
    val name: String,
    val url: String
)