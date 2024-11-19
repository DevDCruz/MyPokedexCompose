package com.example.mypokedexcompose.data.dataSource.local.pokemon

import kotlinx.coroutines.flow.Flow

interface PokemonRoomDataSource {

    fun getPokemonByName(name: String): Flow<PokemonEntity?>

    fun getPokemonById(id: Int): Flow<PokemonEntity?>

    suspend fun savePokemon(pokemonEntity: PokemonEntity)
}
