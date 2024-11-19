package com.example.mypokedexcompose.framework.database.pokedex

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity

class PokedexRoomDataSource(private val pokedexDao: PokedexDao) : PokedexRoomDataSource {

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
