package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokedexcompose.data.pokemon.Pokemon

@Dao
interface PokedexDao {

    @Query("SELECT * FROM Pokemon")
    suspend fun fetchPokedex(): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): Pokemon

    @Query("SELECT COUNT(*) FROM Pokemon")
    suspend fun countPokemons(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemons(pokemons: List<Pokemon>)
}