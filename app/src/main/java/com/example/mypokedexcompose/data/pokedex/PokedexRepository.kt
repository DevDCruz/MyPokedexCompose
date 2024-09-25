package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.dataSource.local.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class PokedexRepository(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokedexLocalDataSource: PokedexLocalDataSource
) {
    val pokemons: Flow<List<Pokemon>> = pokedexLocalDataSource.pokemons

    suspend fun fetchAllPokemons() {
        if (pokedexLocalDataSource.isEmpty()) {
            val remotePokemons = pokedexRemoteDataSource.fetchPokedex(0, 1025)
            pokedexLocalDataSource.savePokemons(remotePokemons)
        }
    }

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {
        return pokedexLocalDataSource.getPokedexForRegion(offset, limit)
    }


}

