package com.example.mypokedexcompose.data.pokemon

import com.example.mypokedexcompose.R
import java.util.Random

class PokemonRepository {

    suspend fun fetchPokemon(name: String): Pokemon = PokemonClient.instance
        .getPokemonByName(name)
        .toDomainModel()


    suspend fun fetchRandomPokemon(): Pokemon = PokemonClient.instance
        .getPokemonById(generateRandomId())
        .toDomainModel()


}

private fun PokemonResult.toDomainModel(): Pokemon =
    Pokemon(
        name = name,
        id = id,
        height = height,
        weight = weight,
        types = types
    )


private fun generateRandomId(): Int = Random().nextInt(1025)