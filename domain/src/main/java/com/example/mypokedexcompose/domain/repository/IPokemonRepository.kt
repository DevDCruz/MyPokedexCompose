package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun fetchPokemonByName(name: String): Flow<PokemonDomain>

    suspend fun fetchRandomPokemon(): Flow<PokemonDomain?>

    suspend fun toggleFavorite(pokemonDomain: PokemonDomain)
}