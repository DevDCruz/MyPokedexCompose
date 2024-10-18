package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult

class PokedexServerDataSource(
    private val pokedexClient: PokedexClient
) : PokedexRemoteDataSource {

    override suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonResult> =
        pokedexClient.instance
            .fectkRegionalPokedex(offset, limit)
            .results
}