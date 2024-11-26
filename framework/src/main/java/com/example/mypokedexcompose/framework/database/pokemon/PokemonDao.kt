package com.example.mypokedexcompose.framework.database.pokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity WHERE name = :name")
    fun getPokemonByName(name: String): Flow<PokemonEntity?>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonEntity?>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: List<PokemonEntity>)
}