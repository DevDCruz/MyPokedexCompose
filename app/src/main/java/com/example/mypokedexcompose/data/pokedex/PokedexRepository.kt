package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon

class PokedexRepository(private val pokedexRemoteDataSource: PokedexRemoteDataSource) {

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> =
        pokedexRemoteDataSource.fetchRegionalPokedex(offset, limit)

}

