package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonResult

class PokedexRepository {

    suspend fun fetchPokedex(): List<Pokemon> = PokedexClient.instance
        .fetchPokedex(1025)
        .results
        .map {
            it.todomainModel()
        }

    suspend fun fetchRegionalPokedex(offset : Int, limit: Int): List<Pokemon> = PokedexClient.instance
        .fectkRegionalPokedex(offset, limit)
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
        types = types
    )