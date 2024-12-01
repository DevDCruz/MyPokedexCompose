package com.example.mypokedexcompose.domain.pokemon


data class PokemonDomain(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>?,
    var favorite: Boolean,
    var detailFetched: Boolean
)

