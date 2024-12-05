package com.example.mypokedexcompose.framework.remote.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.mappers.PokemonMapper

internal class PokemonServerDataSource(
    private val pokemonService: PokemonService,
    private val pokemonMapper: PokemonMapper
) : PokemonRemoteDataSource {
    override suspend fun fetchPokemon(name: String): PokemonDomain = pokemonService
        .getPokemonByName(name).let { pokemonMapper.fromRemoteToDomain(it) }

    override suspend fun fetchRandomPokemon(id: Int): PokemonDomain = pokemonService
        .getPokemonById(id).let { pokemonMapper.fromRemoteToDomain(it) }
}