package com.example.mypokedexcompose.data.items

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val url: String
)