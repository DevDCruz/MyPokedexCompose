package com.example.mypokedexcompose.data.dataSource.local

import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonDao
import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonEntity

class PokemonLocalDataSource(
    private val pokemonDao: PokemonDao
) {

    fun getPokemonByName(name: String) = pokemonDao.getPokemonByName(name)

    fun getPokemonById(id: Int) = pokemonDao.getPokemonById(id)

    suspend fun savePokemon(pokemonEntity: PokemonEntity) =
        pokemonDao.savePokemon(listOf(pokemonEntity))

}