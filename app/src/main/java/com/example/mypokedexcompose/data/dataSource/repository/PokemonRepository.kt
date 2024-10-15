package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.database.pokemon.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.util.Random

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonMapper: PokemonMapper
) {

    suspend fun fetchPokemonDetails(name: String): Flow<Pokemon?> = flow {
        val localPokemonDetail = pokemonLocalDataSource.getPokemonByName(name).firstOrNull()
        if (localPokemonDetail != null && localPokemonDetail.isDetailFetched) {
            Log.d(
                "PokemonRepository",
                "Pokemon details fetched from local DB for ${localPokemonDetail.name}"
            )
            emit(pokemonMapper.toDomain(localPokemonDetail))
        } else {
            val remotePokemonDetail = pokemonRemoteDataSource.fetchPokemon(name)
            val newLocalPokemonDetail = pokemonMapper.fromRemoteToEntity(remotePokemonDetail)
            newLocalPokemonDetail.isDetailFetched = true
            pokemonLocalDataSource.savePokemon(newLocalPokemonDetail)
            Log.d(
                "PokemonRepository",
                "Pokemon details fetched from remote API for ${newLocalPokemonDetail.name}"
            )
            emit(pokemonMapper.toDomain(newLocalPokemonDetail))
        }
    }

    suspend fun fetchRandomPokemon(): Flow<Pokemon?> = flow {

        val localPokemon = pokemonLocalDataSource.getPokemonById(generateRandomId()).firstOrNull()

        if (localPokemon != null) {
            emit(pokemonMapper.toDomain(localPokemon))
        } else {
            val remotePokemon = pokemonRemoteDataSource.fetchRandomPokemon(generateRandomId())
            val newLocalPokemon = pokemonMapper.fromRemoteToEntity(remotePokemon)
            pokemonLocalDataSource.savePokemon(newLocalPokemon)
            emit(pokemonMapper.toDomain(newLocalPokemon))
        }
    }

    suspend fun toggleFavorite(pokemon: Pokemon): Flow<Pokemon?> = flow {
        pokemonLocalDataSource.getPokemonByName(pokemon.name)
            .firstOrNull()?.let { currentPokemonEntity ->
                val updatedPokemonEntity =
                    currentPokemonEntity.copy(favorite = !currentPokemonEntity.favorite)
                pokemonLocalDataSource.savePokemon(updatedPokemonEntity)
                emit(pokemonMapper.toDomain(updatedPokemonEntity))
            } ?: emit(null)
    }
}

private fun generateRandomId(): Int = Random().nextInt(1025)