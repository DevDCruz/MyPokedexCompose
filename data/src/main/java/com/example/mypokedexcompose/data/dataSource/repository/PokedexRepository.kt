package com.example.mypokedexcompose.data.dataSource.repository

import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexServerDataSource
import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokedexRepository(
    private val pokedexServerDataSource: PokedexServerDataSource,
    private val pokedexRoomDataSource: PokedexRoomDataSource,
    private val pokemonMapper: PokemonMapper
) : IPokedexRepository {
    override val pokedex: Flow<List<Pokemon>> =
        pokedexRoomDataSource.pokemons.map { pokemonMapper.toDomainList(it) }

    override suspend fun fetchPokedexComplete() {
        val pokemonsCounts = pokedexRoomDataSource.countPokemons()
        if (pokemonsCounts < 1025) {
            val remotePokemons = pokedexServerDataSource.fetchPokedex(0, 1025)
            val localPokemons = pokemonMapper.fromRemoteToEntityList(remotePokemons)
            pokedexRoomDataSource.savePokemons(localPokemons)
        }
    }

    override suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {
        val localPokemons = pokedexRoomDataSource.getPokedexForRegion(offset, limit)
        return pokemonMapper.toDomainList(localPokemons)
    }
}

