package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.database.pokemon.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Random

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonMapper: PokemonMapper
) {

    fun fetchPokemonDetails(name: String): Flow<Pokemon?> = flow {
        val localPokemonDetail = pokemonLocalDataSource.getPokemonByName(name).firstOrNull()

        if (localPokemonDetail != null && localPokemonDetail.isDetailFetched) {
            Log.d(
                "PokemonRepository",
                "Pokemon details fetched from local DB for ${localPokemonDetail.name}"
            )
            emit(pokemonMapper.toDomain(localPokemonDetail))
        } else {
            Log.d("PokemonRepository", "Pokemon details fetched from remote for $name")
            val remotePokemonDetail = pokemonRemoteDataSource.fetchPokemon(name)
            val newLocalPokemonDetail = pokemonMapper.fromRemoteToEntity(remotePokemonDetail)
            newLocalPokemonDetail.isDetailFetched = true
            pokemonLocalDataSource.savePokemon(newLocalPokemonDetail)
            emit(pokemonMapper.toDomain(newLocalPokemonDetail))
        }
        pokemonLocalDataSource.getPokemonByName(name)
            .map { pokemonEntity -> pokemonEntity?.let { pokemonMapper.toDomain(it) } }
            .collect { emit(it) }
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

    suspend fun toggleFavorite(pokemon: Pokemon) {
        val currentEntity = pokemonLocalDataSource.getPokemonByName(pokemon.name).firstOrNull()
        if (currentEntity != null) {
            val updatedPokemon = currentEntity.copy(
                favorite = !pokemon.favorite,
                isDetailFetched = currentEntity.isDetailFetched
            )
            pokemonLocalDataSource.savePokemon(updatedPokemon)
        }
    }
}
private fun generateRandomId(): Int = Random().nextInt(1025)