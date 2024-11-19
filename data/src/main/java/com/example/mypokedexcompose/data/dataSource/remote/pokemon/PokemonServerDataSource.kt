package com.example.mypokedexcompose.data.dataSource.remote.pokemon

interface PokemonServerDataSource {
    suspend fun fetchPokemon(name: String): PokemonResult

    suspend fun fetchRandomPokemon(id: Int): PokemonResult
}


