package com.example.mypokedexcompose

import android.app.Application
import com.example.mypokedexcompose.framework.frameworkModules
import com.example.mypokedexcompose.usecase.useCaseModules
import dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                dataModules +
                        useCaseModules +
                        frameworkModules +
                        viewModelModules
            )
        }
    }

}