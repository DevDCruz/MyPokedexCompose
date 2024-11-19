package com.example.mypokedexcompose.framework.database.pokemon

import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonRoomDataSource

class PokemonRoomDataSource(
    private val pokemonDao: PokemonDao
) : PokemonRoomDataSource {

    override fun getPokemonByName(name: String) = pokemonDao.getPokemonByName(name)

    override fun getPokemonById(id: Int) = pokemonDao.getPokemonById(id)

    override suspend fun savePokemon(pokemonEntity: PokemonEntity) =
        pokemonDao.savePokemon(listOf(pokemonEntity))
}