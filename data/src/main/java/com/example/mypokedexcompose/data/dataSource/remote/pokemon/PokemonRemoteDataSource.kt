package com.example.mypokedexcompose.data.dataSource.remote.pokemon

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain

interface PokemonRemoteDataSource {
    suspend fun fetchPokemon(name: String): PokemonDomain

    suspend fun fetchRandomPokemon(id: Int): PokemonDomain
}


