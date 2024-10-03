package com.example.mypokedexcompose.data.pokemon


data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>?,
    var favorite: Boolean
)

