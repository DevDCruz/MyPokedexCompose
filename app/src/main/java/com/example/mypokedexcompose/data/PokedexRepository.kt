package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.PokemonResult
import com.example.mypokedexcompose.data.detail.pokemonresult.Sprites


class PokedexRepository {

    suspend fun getSpriteUrl(id: Int): Sprites =
        PokemonClient
            .instance
            .getSpriteUrl(id)

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
        sprites = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{$id}.png",
        spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{$id}.png"
    )

