package com.example.mypokedexcompose.data.dataSource.remote.pokemon

interface PokemonRemoteDataSource {
    suspend fun fetchPokemon(name: String): PokemonResult

    suspend fun fetchRandomPokemon(id: Int): PokemonResult
}


