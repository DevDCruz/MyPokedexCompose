package com.example.mypokedexcompose.data.dataSource.local

import com.example.mypokedexcompose.data.dataSource.database.PokemonDao
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokemonLocalDataSource(
    private val pokemonDao: PokemonDao
) {

    fun getPokemonByName(name: String) = pokemonDao.getPokemonByName(name)

    fun getPokemonById(id: Int) = pokemonDao.getPokemonById(id)

    suspend fun savePokemon(pokemon: List<Pokemon>) = pokemonDao.savePokemon(pokemon)

}