package com.example.mypokedexcompose

import android.app.Application
import com.example.mypokedexcompose.framework.frameWorkMappersModule
import com.example.mypokedexcompose.framework.frameworkBackPackItemModule
import com.example.mypokedexcompose.framework.frameworkBerryModule
import com.example.mypokedexcompose.framework.frameworkPokedexModule
import com.example.mypokedexcompose.framework.frameworkPokemonModule
import com.example.mypokedexcompose.framework.frameworkRegionModule
import com.example.mypokedexcompose.framework.frameworkRetrofitModule
import com.example.mypokedexcompose.framework.frameworkRoomModule
import com.example.mypokedexcompose.usecase.useCaseBackPackItemModule
import com.example.mypokedexcompose.usecase.useCaseBerryModule
import com.example.mypokedexcompose.usecase.useCasePokedexModule
import com.example.mypokedexcompose.usecase.useCasePokemonModule
import dataBackPackModule
import dataBerryModule
import dataPokedexModule
import dataPokemonModule
import dataRegionModule
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
                viewModelHomeModule,
                viewModelPokedexModule,
                viewModelDetailModule,
                viewModelBerryModule,
                viewModelBackPackModule,
                dataPokemonModule,
                dataPokedexModule,
                dataBerryModule,
                dataBackPackModule,
                dataRegionModule,
                frameworkRoomModule,
                frameworkRetrofitModule,
                frameworkPokemonModule,
                frameworkPokedexModule,
                frameworkBerryModule,
                frameworkBackPackItemModule,
                frameworkRegionModule,
                frameWorkMappersModule,
                useCasePokemonModule,
                useCasePokedexModule,
                useCaseBerryModule,
                useCaseBackPackItemModule
            )
        }
    }

}