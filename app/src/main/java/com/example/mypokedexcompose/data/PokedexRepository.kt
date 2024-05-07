package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.PokemonResult


class PokedexRepository {

    suspend fun fetchPokedex(): List<Pokemon> = PokemonClient
        .instance
        .fetchPokedex(151)
        .results
        .map {
            it.todomainModel()
        }

}

private fun PokemonResult.todomainModel(): Pokemon =
    Pokemon(
        name = name,
        id = id,
        height = height,
        weight = weight,
        spriteDetail = "",
        spritePokedex = ""
    )

