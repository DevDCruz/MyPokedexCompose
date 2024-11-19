package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexServerDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult

class PokedexServerDataSource(
    private val pokedexClient: PokedexClient
) : PokedexServerDataSource {

    override suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonResult> =
        pokedexClient.instance
            .fectkRegionalPokedex(offset, limit)
            .results
}