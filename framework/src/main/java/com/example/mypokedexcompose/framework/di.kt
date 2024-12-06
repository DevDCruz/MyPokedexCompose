package com.example.mypokedexcompose.framework

import android.app.Application
import android.location.Geocoder
import androidx.room.Room
import com.example.mypokedexcompose.data.dataSource.LocationDataSource
import com.example.mypokedexcompose.data.dataSource.RegionDataSource
import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.local.berries.BerryLocalDataSource
import com.example.mypokedexcompose.data.dataSource.local.pokedex.PokedexLocalDataSource
import com.example.mypokedexcompose.data.dataSource.local.pokemon.PokemonLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.berry.BerryRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexRemoteDataSource
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.framework.database.PokedexDatabase
import com.example.mypokedexcompose.framework.database.backpack.BackPackRoomDataSource
import com.example.mypokedexcompose.framework.database.berries.BerryRoomDataSource
import com.example.mypokedexcompose.framework.database.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.framework.database.pokemon.PokemonRoomDataSource
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import com.example.mypokedexcompose.framework.mappers.ItemsMapper
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import com.example.mypokedexcompose.framework.mappers.RegionMapper
import com.example.mypokedexcompose.framework.region.GeocoderRegionDataSource
import com.example.mypokedexcompose.framework.region.PlayServicesLocationDataSource
import com.example.mypokedexcompose.framework.remote.backpack.BackPackServerDataSource
import com.example.mypokedexcompose.framework.remote.backpack.BackpackItemService
import com.example.mypokedexcompose.framework.remote.berries.BerryServerDataSource
import com.example.mypokedexcompose.framework.remote.berries.BerryService
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexServerDataSource
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexService
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonServerDataSource
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkRoomModule {
    @Provides
    @Singleton
    fun provideDataBase(app: Application) =
        Room.databaseBuilder(app, PokedexDatabase::class.java, "pokedex.db").build()

    @Provides
    @Singleton
    fun providePokemonDao(db: PokedexDatabase) = db.pokemonDao()

    @Provides
    @Singleton
    fun provideBerryDao(db: PokedexDatabase) = db.berryDao()

    @Provides
    @Singleton
    fun provideBackPackDao(db: PokedexDatabase) = db.BackPackDao()

    @Provides
    @Singleton
    fun providePokedexDao(db: PokedexDatabase) = db.pokedexDao()
}

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkRetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit) = retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun provideBerryService(retrofit: Retrofit) = retrofit.create(BerryService::class.java)

    @Provides
    @Singleton
    fun providePokedexService(retrofit: Retrofit) = retrofit.create(PokedexService::class.java)

    @Provides
    @Singleton
    fun provideBackpackItemService(retrofit: Retrofit) =
        retrofit.create(BackpackItemService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkLocalDataSourceModule {

    @Binds
    abstract fun bindPokedexLocalDataSource(pokedexLocalDataSource: PokedexRoomDataSource): PokedexLocalDataSource

    @Binds
    abstract fun bindPokedexRemoteDataSource(pokedexRemoteDataSource: PokedexServerDataSource): PokedexRemoteDataSource

    @Binds
    abstract fun bindPokemonLocalDataSource(pokemonLocalDataSource: PokemonRoomDataSource): PokemonLocalDataSource

    @Binds
    abstract fun bindPokemonRemoteDataSource(pokemonRemoteDataSource: PokemonServerDataSource): PokemonRemoteDataSource

    @Binds
    abstract fun binBackpackItemLocalDataSource(backpackItemLocalDataSource: BackPackRoomDataSource): BackPackLocalDataSource

    @Binds
    abstract fun binBackpackItemRemoteDataSource(backpackItemRemoteDataSource: BackPackServerDataSource): BackPackRemoteDataSource

    @Binds
    abstract fun bindBerryLocalDataSource(berryLocalDataSource: BerryRoomDataSource): BerryLocalDataSource

    @Binds
    abstract fun bindBerryRemoteDataSource(berryRemoteDataSource: BerryServerDataSource): BerryRemoteDataSource
}


@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkRegionBindsModule {

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindRegionDataSource(regionDataSource: GeocoderRegionDataSource): RegionDataSource

}

@Module
@InstallIn(SingletonComponent::class)
internal class FrameworkRegionModule {
    @Provides
    fun provideFusedLocationDataSource(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    fun provideGeocoder(app: Application): Geocoder {
        return Geocoder(app)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkMappersModule {
    @Provides
    @Singleton
    fun providePokemonMapper() = PokemonMapper()

    @Provides
    @Singleton
    fun provideItemsMapper() = ItemsMapper()

    @Provides
    @Singleton
    fun provideBerryMapper() = BerryMapper()

    @Provides
    @Singleton
    fun provideRegionMapper() = RegionMapper()
}
