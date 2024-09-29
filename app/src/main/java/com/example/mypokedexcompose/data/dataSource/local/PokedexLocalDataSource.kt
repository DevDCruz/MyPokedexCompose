package com.example.mypokedexcompose.data.dataSource.local

import com.example.mypokedexcompose.data.dataSource.database.pokedex.PokedexDao
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokedexLocalDataSource(private val pokedexDao: PokedexDao) {

    val pokemons = pokedexDao.fetchPokedex()

    suspend fun isEmpty() = pokedexDao.countPokemons() == 0

    suspend fun savePokemons(pokemons: List<Pokemon>) = pokedexDao.savePokemons(pokemons)

    suspend fun getPokedexForRegion(offset: Int, limit: Int): List<Pokemon> =
        pokedexDao.fetchRegionalPokedex(offset, limit)


}