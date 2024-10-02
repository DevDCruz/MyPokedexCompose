package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {

    @Query("SELECT * FROM PokemonEntity")
    fun fetchPokedex(): Flow<List<PokemonEntity>>

    @Query("SELECT COUNT(*) FROM PokemonEntity")
    suspend fun countPokemons(): Int

    @Query("SELECT * FROM PokemonEntity LIMIT :limit OFFSET :offset")
    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<PokemonEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemons(pokemons: List<PokemonEntity>)
}