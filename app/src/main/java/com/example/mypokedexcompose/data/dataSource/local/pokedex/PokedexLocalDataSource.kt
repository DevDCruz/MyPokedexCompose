package com.example.mypokedexcompose.data.dataSource.local.pokedex

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity

class PokedexLocalDataSource(private val pokedexDao: PokedexDao) {

    val pokemons = pokedexDao.fetchPokedex()

    suspend fun isEmpty() = pokedexDao.countPokemons() == 0

    suspend fun savePokemons(pokemonEntity: List<PokemonEntity>) {
        Log.d("PokedexLocalDataSource", "savingPokemons: ${pokemonEntity.size}")
        pokedexDao.savePokemons(pokemonEntity)
        val newCount = countPokemons()
        Log.d("PokedexLocalDataSource", "newCount: $newCount")
    }

    suspend fun countPokemons(): Int {
        val count = pokedexDao.countPokemons()
        Log.d("PokedexLocalDataSource", "countPokemons: $count")
        return count
    }


    suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonEntity> =
        pokedexDao.fetchRegionalPokedex(offset, limit)
}

