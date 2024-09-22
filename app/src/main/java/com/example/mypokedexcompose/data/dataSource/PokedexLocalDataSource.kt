package com.example.mypokedexcompose.data.dataSource

import com.example.mypokedexcompose.data.dataSource.database.pokedex.PokedexDao
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokedexLocalDataSource(private val pokedexDao: PokedexDao) {

    suspend fun fetchPokedex(offset: Int, limit: Int): List<Pokemon> = pokedexDao.fetchPokedex()

    suspend fun getPokemonById(id: Int): Pokemon? = pokedexDao.getPokemonById(id)

    suspend fun isEmpty() = pokedexDao.countPokemons() == 0

    suspend fun savePokemons(pokemons: List<Pokemon>) = pokedexDao.savePokemons(pokemons)
}