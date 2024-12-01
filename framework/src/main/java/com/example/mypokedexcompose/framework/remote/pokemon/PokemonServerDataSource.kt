package com.example.mypokedexcompose.framework.remote.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.mappers.PokemonMapper

class PokemonServerDataSource(
    private val pokemonClient: PokemonClient,
    private val pokemonMapper: PokemonMapper
) : PokemonRemoteDataSource {
    override suspend fun fetchPokemon(name: String): PokemonDomain = pokemonClient.instance
        .getPokemonByName(name).let { pokemonMapper.fromRemoteToDomain(it) }

    override suspend fun fetchRandomPokemon(id: Int): PokemonDomain = pokemonClient.instance
        .getPokemonById(id).let { pokemonMapper.fromRemoteToDomain(it) }
}