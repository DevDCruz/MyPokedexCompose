package com.example.mypokedexcompose.data.items

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val name: String,
    val url: String
)