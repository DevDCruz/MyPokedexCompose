package com.example.mypokedexcompose.data.dataSource.remote.pokedex


import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult

data class PokedexResult(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonResult>
)





