package com.example.mypokedexcompose.data.detail.pokemonresult

import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.PokemonClient
import com.example.mypokedexcompose.data.detail.pokemonresult.spritesfolder.Sprites

class PokemonRepository {

    suspend fun fetchPokemon(name: String): Pokemon = PokemonClient
    .instance
    .getPokemonByName(name)
    .todomainModel()


}

private fun PokemonResult.todomainModel(): Pokemon =
    Pokemon(
        name = name,
        id = id,
        height = height,
        weight = weight,
        sprites = sprites,
        spritePokedex = "\"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{$id}.png\""
    )