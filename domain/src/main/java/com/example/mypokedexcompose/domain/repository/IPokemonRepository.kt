package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun fetchPokemonByName(name: String): Flow<Pokemon>

    suspend fun fetchRandomPokemon(): Flow<Pokemon?>

    suspend fun toggleFavorite(pokemon: Pokemon)
}