package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.framework.remote.pokemon.PokemonResult

data class PokedexResult(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonResult>
)





