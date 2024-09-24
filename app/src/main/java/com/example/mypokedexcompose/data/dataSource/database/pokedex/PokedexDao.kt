package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {

    @Query("SELECT * FROM Pokemon")
    fun fetchPokedex(): Flow<List<Pokemon>>

    @Query("SELECT COUNT(*) FROM Pokemon")
    suspend fun countPokemons(): Int

    @Query("SELECT * FROM Pokemon LIMIT :limit OFFSET :offset")
    suspend fun fetchRegionalPokedex(offset: Int, limit: Int): List<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemons(pokemons: List<Pokemon>)
}