package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.domaindatalayer.pokemon.Pokemon
import com.example.mypokedexcompose.framework.database.pokemon.PokemonRoomDataSource
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonServerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Random

class PokemonRepository(
    private val pokemonServerDataSource: PokemonServerDataSource,
    private val pokemonRoomDataSource: PokemonRoomDataSource,
    private val pokemonMapper: PokemonMapper
) {

    fun fetchPokemonByName(name: String): Flow<Pokemon> = flow {
        val localPokemonDetail = pokemonRoomDataSource.getPokemonByName(name).firstOrNull()

        if (localPokemonDetail != null && localPokemonDetail.isDetailFetched) {
            Log.d(
                "PokemonRepository",
                "Pokemon details fetched from local DB for ${localPokemonDetail.name}"
            )
            emit(pokemonMapper.toDomain(localPokemonDetail))
        } else {
            Log.d("PokemonRepository", "Pokemon details fetched from remote for $name")
            val remotePokemonDetail = pokemonServerDataSource.fetchPokemon(name)
            val newLocalPokemonDetail = pokemonMapper.fromRemoteToEntity(remotePokemonDetail)
            newLocalPokemonDetail.isDetailFetched = true
            pokemonRoomDataSource.savePokemon(newLocalPokemonDetail)
            emit(pokemonMapper.toDomain(newLocalPokemonDetail))
        }
        pokemonRoomDataSource.getPokemonByName(name)
            .map { pokemonEntity -> pokemonEntity?.let { pokemonMapper.toDomain(it) } }
            .collect { emit(it) }
    }
        .filterNotNull()

    suspend fun fetchRandomPokemon(): Flow<Pokemon?> = flow {
        val localPokemon = pokemonRoomDataSource.getPokemonById(generateRandomId()).firstOrNull()
        if (localPokemon != null) {
            emit(pokemonMapper.toDomain(localPokemon))
        } else {
            val remotePokemon = pokemonServerDataSource.fetchRandomPokemon(generateRandomId())
            val newLocalPokemon = pokemonMapper.fromRemoteToEntity(remotePokemon)
            pokemonRoomDataSource.savePokemon(newLocalPokemon)
            emit(pokemonMapper.toDomain(newLocalPokemon))
        }
    }

    suspend fun toggleFavorite(pokemon: Pokemon) {
        val currentEntity = pokemonRoomDataSource.getPokemonByName(pokemon.name).firstOrNull()
        if (currentEntity != null) {
            val updatedPokemon = currentEntity.copy(
                favorite = !pokemon.favorite,
                isDetailFetched = currentEntity.isDetailFetched
            )
            pokemonRoomDataSource.savePokemon(updatedPokemon)
        }
    }
}

private fun generateRandomId(): Int = Random().nextInt(1025)