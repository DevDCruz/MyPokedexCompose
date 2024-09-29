package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonDao
import com.example.mypokedexcompose.data.pokemon.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao
}