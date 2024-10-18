package com.example.mypokedexcompose.data.dataSource.repository

import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokedexRepository(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokedexLocalDataSource: PokedexLocalDataSource,
    private val pokemonMapper: PokemonMapper
) {
    val pokedex: Flow<List<Pokemon>> =
        pokedexLocalDataSource.pokemons.map { pokemonMapper.toDomainList(it) }

    suspend fun fetchPokedexComplete() {
        val pokemonsCounts = pokedexLocalDataSource.countPokemons()
        if (pokemonsCounts < 1025) {
            val remotePokemons = pokedexRemoteDataSource.fetchPokedex(0, 1025)
            val localPokemons = pokemonMapper.fromRemoteToEntityList(remotePokemons)
            pokedexLocalDataSource.savePokemons(localPokemons)
        }
    }

    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {
        val localPokemons = pokedexLocalDataSource.getPokedexForRegion(offset, limit)
        return pokemonMapper.toDomainList(localPokemons)
    }
}

