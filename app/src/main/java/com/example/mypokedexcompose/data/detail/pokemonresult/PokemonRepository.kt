package com.example.mypokedexcompose.data.detail.pokemonresult

import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.PokemonClient
import java.util.Random

class PokemonRepository {

    suspend fun fetchPokemon(name: String): Pokemon = PokemonClient
        .instance
        .getPokemonByName(name)
        .toDomainModel()


    suspend fun fetchRandomPokemon(): Pokemon = PokemonClient
        .instance
        .getPokemonById(generateRandomId())
        .toDomainModel()


}

private fun PokemonResult.toDomainModel(): Pokemon =
    Pokemon(
        name = name,
        id = id,
        height = height,
        weight = weight,
        types = types,
        spriteDetail = "",
        spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{$id}.png"
    )


private fun generateRandomId(): Int = Random().nextInt(1025)
