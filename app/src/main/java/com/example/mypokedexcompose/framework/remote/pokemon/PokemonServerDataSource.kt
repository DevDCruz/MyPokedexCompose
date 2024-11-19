package com.example.mypokedexcompose.framework.remote.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource

class PokemonServerDataSource(
    private val pokemonClient: PokemonClient
) : PokemonRemoteDataSource {
    override suspend fun fetchPokemon(name: String): PokemonResult = pokemonClient.instance
        .getPokemonByName(name)

    override suspend fun fetchRandomPokemon(id: Int): PokemonResult = pokemonClient.instance
        .getPokemonById(id)
}