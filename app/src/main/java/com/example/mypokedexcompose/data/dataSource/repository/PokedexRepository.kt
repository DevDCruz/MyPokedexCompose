package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.mappers.PokemonMapper
import com.example.mypokedexcompose.data.dataSource.local.database.pokedex.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokedexRepository(
    private val pokedexRemoteDataSource: PokedexRemoteDataSource,
    private val pokedexLocalDataSource: PokedexLocalDataSource,
    private val pokemonMapper: PokemonMapper
) {
    val pokemons: Flow<List<Pokemon>> =
        pokedexLocalDataSource.pokemons.map { pokemonMapper.toDomainList(it) }

    suspend fun fetchAllPokemons() {
        val pokemonsCounts = pokedexLocalDataSource.countPokemons()
        Log.d("PokedexRepository", "FirstcountPokemons: $pokemonsCounts")
        if (pokemonsCounts < 1025) {
            val remotePokemons = pokedexRemoteDataSource.fetchPokedex(0, 1025)
            Log.d("PokedexRepository", "remotePokemons: ${remotePokemons.size}")
            val localPokemons = pokemonMapper.fromRemoteToEntityList(remotePokemons)
            Log.d("PokedexRepository", "localPokemonsMapped: ${localPokemons.size}${localPokemons[26]}")
            try {
                pokedexLocalDataSource.savePokemons(localPokemons)
                Log.d("PokedexRepository", "localPokemonsSaved. Total: ${pokedexLocalDataSource.countPokemons()}")
            } catch (e: Exception) {
                Log.d("PokedexRepository", "error: ${e.message}")
            }
            }

        }


    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon> {
        val localPokemons = pokedexLocalDataSource.getPokedexForRegion(offset, limit)
        return pokemonMapper.toDomainList(localPokemons)
    }
}

