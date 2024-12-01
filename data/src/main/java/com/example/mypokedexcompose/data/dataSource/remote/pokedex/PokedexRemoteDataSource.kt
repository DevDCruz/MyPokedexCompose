package com.example.mypokedexcompose.data.dataSource.remote.pokedex

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain

interface PokedexRemoteDataSource {
    suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonDomain>
}
