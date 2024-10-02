package com.example.mypokedexcompose.data.dataSource.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult

class PokedexRemoteDataSource {

    suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonResult> =
        PokedexClient.instance
            .fectkRegionalPokedex(offset, limit)
            .results
}