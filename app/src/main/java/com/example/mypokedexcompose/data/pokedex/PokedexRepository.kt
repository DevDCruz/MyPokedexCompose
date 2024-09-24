package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.dataSource.local.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class PokedexRepository(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokedexLocalDataSource: PokedexLocalDataSource
) {
    val pokemons: Flow<List<Pokemon>> = pokedexLocalDataSource.pokemons.transform { localPokemons ->
        val pokemons = localPokemons.takeIf { it.isNotEmpty() }
            ?: pokedexRemoteDataSource.fetchPokedex(0, 1010)
                .also {
                    pokedexLocalDataSource.savePokemons(it)
                }
        emit(pokemons)
    }

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {
        val localPokemons = pokedexLocalDataSource.getPokedexForRegion(offset, limit)
        return if (localPokemons.isNotEmpty()) {
            localPokemons
        } else {
            val remotePokemons = pokedexRemoteDataSource.fetchPokedex(offset, limit)
            pokedexLocalDataSource.savePokemons(remotePokemons)
            remotePokemons
        }
    }
}

