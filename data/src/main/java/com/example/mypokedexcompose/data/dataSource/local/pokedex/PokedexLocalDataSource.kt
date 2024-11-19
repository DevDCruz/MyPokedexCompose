package com.example.mypokedexcompose.data.dataSource.local.pokedex

import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokedexLocalDataSource {

    val pokemons: Flow<List<PokemonEntity>>

    suspend fun savePokemons(pokemonEntity: List<PokemonEntity>)

    suspend fun countPokemons(): Int

    suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonEntity>
}

