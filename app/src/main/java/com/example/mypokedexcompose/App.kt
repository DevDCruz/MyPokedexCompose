package com.example.mypokedexcompose

import android.app.Application
import androidx.room.Room
import com.example.mypokedexcompose.data.dataSource.database.pokedex.PokedexDatabase

class App : Application() {

    lateinit var db: PokedexDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, PokedexDatabase::class.java, "pokedex.db").build()
    }

}