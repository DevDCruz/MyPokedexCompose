package com.example.mypokedexcompose.data.dataSource.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult

interface PokedexRemoteDataSource {
    suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonResult>
}
