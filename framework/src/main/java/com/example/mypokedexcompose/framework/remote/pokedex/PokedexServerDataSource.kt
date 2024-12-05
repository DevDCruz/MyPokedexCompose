package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.framework.mappers.PokemonMapper

internal class PokedexServerDataSource(
    private val pokedexService: PokedexService,
    private val pokemonMapper: PokemonMapper
) : PokedexRemoteDataSource {

    override suspend fun fetchPokedex(offset: Int, limit: Int): List<PokemonDomain> =
        pokedexService
            .fectkRegionalPokedex(offset, limit)
            .results.map { pokemons -> pokemonMapper.fromRemoteToDomain(pokemons) }
}