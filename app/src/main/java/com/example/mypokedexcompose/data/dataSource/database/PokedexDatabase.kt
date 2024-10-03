package com.example.mypokedexcompose.data.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypokedexcompose.data.dataSource.database.berries.BerryDao
import com.example.mypokedexcompose.data.dataSource.database.berries.BerryEntity
import com.example.mypokedexcompose.data.dataSource.database.pokedex.PokedexDao
import com.example.mypokedexcompose.data.dataSource.database.pokemon.Converters
import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonDao
import com.example.mypokedexcompose.data.dataSource.database.pokemon.PokemonEntity

@Database(entities = [PokemonEntity::class, BerryEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun berryDao(): BerryDao
}