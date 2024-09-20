package com.example.mypokedexcompose.data.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource

class PokemonRepository(private val remoteDataSource: PokemonRemoteDataSource) {

    suspend fun fetchPokemon(name: String): Pokemon = remoteDataSource.fetchPokemon(name)

    suspend fun fetchRandomPokemon(): Pokemon = remoteDataSource.fetchRandomPokemon()

}
