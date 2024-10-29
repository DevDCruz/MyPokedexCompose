package com.example.mypokedexcompose.domaindatalayer.pokemon


data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>?,
    var favorite: Boolean
)

