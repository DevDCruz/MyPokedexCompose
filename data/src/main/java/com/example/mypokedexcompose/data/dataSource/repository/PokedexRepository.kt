package com.example.mypokedexcompose.data.dataSource.repository

import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class PokedexRepository(
    private val pokedexServerDataSource: PokedexRemoteDataSource,
    private val pokedexRoomDataSource: PokedexLocalDataSource
) : IPokedexRepository {
    override val pokedex: Flow<List<PokemonDomain>> =
        pokedexRoomDataSource.pokemons

    override suspend fun fetchPokedexComplete() {
        val pokemonsCounts = pokedexRoomDataSource.countPokemons()
        if (pokemonsCounts < 1025) {
            val remotePokemons = pokedexServerDataSource.fetchPokedex(0, 1025)
            pokedexRoomDataSource.savePokemons(remotePokemons)
        }
    }

    override suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<PokemonDomain> {
        val localPokemons = pokedexRoomDataSource.getPokedexForRegion(offset, limit)
        return localPokemons
    }
}

