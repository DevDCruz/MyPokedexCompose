package com.example.mypokedexcompose.data.dataSource.local.pokemon

import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    fun getPokemonByName(name: String): Flow<PokemonEntity?>

    fun getPokemonById(id: Int): Flow<PokemonEntity?>

    suspend fun savePokemon(pokemonEntity: PokemonEntity)
}

class PokemonRoomDataSource(
    private val pokemonDao: PokemonDao
) : PokemonLocalDataSource {

    override fun getPokemonByName(name: String) = pokemonDao.getPokemonByName(name)

    override fun getPokemonById(id: Int) = pokemonDao.getPokemonById(id)

    override suspend fun savePokemon(pokemonEntity: PokemonEntity) =
        pokemonDao.savePokemon(listOf(pokemonEntity))
}