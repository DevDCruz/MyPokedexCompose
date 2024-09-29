package com.example.mypokedexcompose.data.dataSource.database.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM Pokemon WHERE name = :name")
    fun getPokemonByName(name: String): Flow<Pokemon?>

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    fun getPokemonById(id: Int): Flow<Pokemon?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: List<Pokemon>)
}