package com.example.mypokedexcompose.data.dataSource.local.pokemon

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    fun getPokemonByName(name: String): Flow<PokemonDomain?>

    fun getPokemonById(id: Int): Flow<PokemonDomain?>

    suspend fun savePokemon(pokemonDomain: PokemonDomain)
}
