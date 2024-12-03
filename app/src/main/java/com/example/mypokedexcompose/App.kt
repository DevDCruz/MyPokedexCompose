package com.example.mypokedexcompose

import android.app.Application
import androidx.room.Room
import com.example.mypokedexcompose.framework.database.PokedexDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    lateinit var db: PokedexDatabase

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
        }

        db = Room.databaseBuilder(this, PokedexDatabase::class.java, "pokedex.db").build()
    }

}