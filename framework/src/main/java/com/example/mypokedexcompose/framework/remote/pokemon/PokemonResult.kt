package com.example.mypokedexcompose.framework.remote.pokemon


import com.example.mypokedexcompose.domain.pokemon.Type

data class PokemonResult(
    val height: Int,
    val id: Int,
    val name: String,
    val types: List<Type>?,
    val url: String,
    val weight: Int
)