package com.example.mypokedexcompose.data.dataSource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypokedexcompose.data.dataSource.local.database.backpack.BackPackDao
import com.example.mypokedexcompose.data.dataSource.local.database.backpack.ItemEntity
import com.example.mypokedexcompose.data.dataSource.local.database.berries.BerryDao
import com.example.mypokedexcompose.data.dataSource.local.database.berries.BerryEntity
import com.example.mypokedexcompose.data.dataSource.local.database.pokedex.PokedexDao
import com.example.mypokedexcompose.data.dataSource.local.database.pokemon.PokemonDao
import com.example.mypokedexcompose.data.dataSource.local.database.pokemon.PokemonEntity

@Database(
    entities = [PokemonEntity::class, BerryEntity::class, ItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun berryDao(): BerryDao
    abstract fun BackPackDao(): BackPackDao
}