package com.example.mypokedexcompose.data.pokemon

import com.example.mypokedexcompose.data.dataSource.local.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.util.Random

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) {

    suspend fun fetchPokemon(name: String): Flow<Pokemon?> =
        pokemonLocalDataSource.getPokemonByName(name).transform { localPokemon ->
            val pokemon = localPokemon ?: pokemonRemoteDataSource.fetchPokemon(name).also {
                pokemonLocalDataSource.savePokemon(listOf(it))
            }
            emit(pokemon)
        }

    suspend fun fetchRandomPokemon(): Flow<Pokemon?> =
        pokemonLocalDataSource.getPokemonById(generateRandomId()).transform { localPokemon ->
            val pokemon =
                localPokemon ?: pokemonRemoteDataSource.fetchRandomPokemon(generateRandomId())
                    .also {
                        pokemonLocalDataSource.savePokemon(listOf(it))
                    }
            emit(pokemon)
        }

    suspend fun toggleFavorite(pokemon: Pokemon) {
        pokemonLocalDataSource.savePokemon(listOf(pokemon.copy(favorite = !pokemon.favorite)))
    }
}

private fun generateRandomId(): Int = Random().nextInt(1025)