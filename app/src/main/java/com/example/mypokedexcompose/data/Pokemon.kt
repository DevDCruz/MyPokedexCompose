package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.Type

data class Pokemon(
    val name: String,
    val url: String,
    val id: Int,
    val height: Int,
    val weight: Int,
    val sprites: String,
    val types: List<Type>
)

