package com.example.mypokedexcompose.data.dataSource.remote.pokemon

import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonResult
import java.util.Random

class PokemonRemoteDataSource {
    suspend fun fetchPokemon(name: String): Pokemon = PokemonClient.instance
        .getPokemonByName(name)
        .toDomainModel()

    suspend fun fetchRandomPokemon(id: Int): Pokemon = PokemonClient.instance
        .getPokemonById(id)
        .toDomainModel()


}

private fun PokemonResult.toDomainModel(): Pokemon =
    Pokemon(
        name = name,
        id = id,
        height = height,
        weight = weight,
        types = types,
        favorite = false
    )