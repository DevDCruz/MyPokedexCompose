package com.example.mypokedexcompose.data.dataSource.remote.pokedex

import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonResult

class PokedexRemoteDataSource {

    suspend fun fetchPokedex(offset: Int, limit: Int): List<Pokemon> =
        PokedexClient.instance
            .fectkRegionalPokedex(offset, limit)
            .results
            .map {
                it.todomainModel()
            }

    private fun PokemonResult.todomainModel(): Pokemon =
        Pokemon(
            name = name,
            id = id,
            height = height,
            weight = weight,
            types = types,
            favorite = false
        )

}