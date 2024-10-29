package com.example.mypokedexcompose.data.dataSource.remote.pokemon


import com.example.mypokedexcompose.domaindatalayer.pokemon.Type

data class PokemonResult(
    val height: Int,
    val id: Int,
    val name: String,
    val types: List<Type>?,
    val url: String,
    val weight: Int
)