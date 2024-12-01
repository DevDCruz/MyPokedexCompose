package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow

interface IPokedexRepository {
    val pokedex: Flow<List<PokemonDomain>>

    suspend fun fetchPokedexComplete()

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<PokemonDomain>
}