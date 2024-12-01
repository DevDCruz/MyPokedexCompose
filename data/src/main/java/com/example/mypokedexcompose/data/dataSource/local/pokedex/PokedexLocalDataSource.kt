package com.example.mypokedexcompose.data.dataSource.local.pokedex

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow

interface PokedexLocalDataSource {

    val pokemons: Flow<List<PokemonDomain>>

    suspend fun savePokemons(pokemonEntity: List<PokemonDomain>)

    suspend fun countPokemons(): Int

    suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonDomain>
}

