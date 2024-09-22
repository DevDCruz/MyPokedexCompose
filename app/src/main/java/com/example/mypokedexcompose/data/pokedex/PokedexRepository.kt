package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.dataSource.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokedexRepository(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokedexLocalDataSource: PokedexLocalDataSource
) {

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {

        if (pokedexLocalDataSource.isEmpty()) {
            pokedexLocalDataSource.savePokemons(
                pokedexRemoteDataSource.fetchRegionalPokedex(offset, limit)
            )
        }
        return pokedexLocalDataSource.fetchPokedex(offset, limit)
    }
}

