package com.example.mypokedexcompose


import com.example.mypokedexcompose.data.DatasModule
import android.app.Application
import com.example.mypokedexcompose.framework.FrameworkDataSourcesModule
import com.example.mypokedexcompose.framework.frameWorkMappersModule
import com.example.mypokedexcompose.framework.frameworkRegionModule
import com.example.mypokedexcompose.framework.frameworkRetrofitModule
import com.example.mypokedexcompose.framework.frameworkRoomModule
import com.example.mypokedexcompose.usecase.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                DatasModule().module,
                FrameworkDataSourcesModule().module,
                frameworkRoomModule,
                frameworkRetrofitModule,
                frameworkRegionModule,
                frameWorkMappersModule,
                UseCaseModule().module
            )
        }
    }

}