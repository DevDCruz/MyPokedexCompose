package com.example.mypokedexcompose.data.dataSource.repository


import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.util.Random

class PokemonRepository(
    private val pokemonServerDataSource: PokemonRemoteDataSource,
    private val pokemonRoomDataSource: PokemonLocalDataSource
) : IPokemonRepository {

    override fun fetchPokemonByName(name: String): Flow<PokemonDomain> = flow {
        val localPokemonDetail = pokemonRoomDataSource.getPokemonByName(name).firstOrNull()

        if (localPokemonDetail != null && localPokemonDetail.detailFetched) {
            emit(localPokemonDetail)
        } else {
            val remotePokemonDetail = pokemonServerDataSource.fetchPokemon(name)

            remotePokemonDetail.detailFetched = true
            pokemonRoomDataSource.savePokemon(remotePokemonDetail)
            emit(remotePokemonDetail)
        }
        pokemonRoomDataSource.getPokemonByName(name)
            .collect { pokemon -> pokemon?.let { emit(it) } }
    }
        .filterNotNull()

    override suspend fun fetchRandomPokemon(): Flow<PokemonDomain?> = flow {
        val localPokemon = pokemonRoomDataSource.getPokemonById(generateRandomId()).firstOrNull()
        if (localPokemon != null) {
            emit(localPokemon)
        } else {
            val remotePokemon = pokemonServerDataSource.fetchRandomPokemon(generateRandomId())
            emit(remotePokemon)
        }
    }

    override suspend fun toggleFavorite(pokemonDomain: PokemonDomain) {
        val currentEntity = pokemonRoomDataSource.getPokemonByName(pokemonDomain.name).firstOrNull()
        if (currentEntity != null) {
            val updatedPokemon = currentEntity.copy(
                favorite = !pokemonDomain.favorite,
                detailFetched = currentEntity.detailFetched
            )
            pokemonRoomDataSource.savePokemon(updatedPokemon)
        }
    }
}

private fun generateRandomId(): Int = Random().nextInt(1025)