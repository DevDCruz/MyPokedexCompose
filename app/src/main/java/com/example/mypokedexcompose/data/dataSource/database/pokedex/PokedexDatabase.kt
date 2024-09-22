package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mypokedexcompose.data.pokemon.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
}