package com.example.mypokedexcompose.data.dataSource.database.pokedex

import androidx.room.Database
import com.example.mypokedexcompose.data.pokemon.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokedexDatabase {

    abstract fun pokedexDao(): PokedexDao
}