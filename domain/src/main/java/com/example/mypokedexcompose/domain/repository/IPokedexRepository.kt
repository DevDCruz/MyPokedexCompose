package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokedexRepository {
    val pokedex: Flow<List<Pokemon>>

    suspend fun fetchPokedexComplete()

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon>
}