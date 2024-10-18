package com.example.mypokedexcompose.data.dataSource.local.pokedex

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokedexLocalDataSource {
    val pokemons: Flow<List<PokemonEntity>>

    suspend fun savePokemons(pokemonEntity: List<PokemonEntity>)

    suspend fun countPokemons(): Int

    suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonEntity>
}

class PokedexRoomDataSource(private val pokedexDao: PokedexDao) : PokedexLocalDataSource {

    override val pokemons = pokedexDao.fetchPokedex()

    override suspend fun savePokemons(pokemonEntity: List<PokemonEntity>) {
        Log.d("PokedexLocalDataSource", "savingPokemons: ${pokemonEntity.size}")
        pokedexDao.savePokemons(pokemonEntity)
        val newCount = countPokemons()
        Log.d("PokedexLocalDataSource", "newCount: $newCount")
    }

    override suspend fun countPokemons(): Int {
        val count = pokedexDao.countPokemons()
        Log.d("PokedexLocalDataSource", "countPokemons: $count")
        return count
    }

    override suspend fun getPokedexForRegion(offset: Int, limit: Int): List<PokemonEntity> =
        pokedexDao.fetchRegionalPokedex(offset, limit)
}

