package com.example.mypokedexcompose.framework

import android.content.Context
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
import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonRemoteDataSource
import com.example.mypokedexcompose.framework.database.PokedexDatabase
import com.example.mypokedexcompose.framework.database.backpack.BackPackRoomDataSource
import com.example.mypokedexcompose.framework.database.berries.BerryRoomDataSource
import com.example.mypokedexcompose.framework.database.pokedex.PokedexRoomDataSource
import com.example.mypokedexcompose.framework.database.pokemon.PokemonRoomDataSource
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import com.example.mypokedexcompose.framework.mappers.ItemsMapper
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import com.example.mypokedexcompose.framework.region.GeocoderRegionDataSource
import com.example.mypokedexcompose.framework.region.PlayServicesLocationDataSource
import com.example.mypokedexcompose.framework.remote.backpack.BackPackItemClient
import com.example.mypokedexcompose.framework.remote.backpack.BackPackServerDataSource
import com.example.mypokedexcompose.framework.remote.berries.BerryClient
import com.example.mypokedexcompose.framework.remote.berries.BerryServerDataSource
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexClient
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonClient
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonServerDataSource
import com.google.android.gms.location.LocationServices
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkRoomModule = module {
    single { Room.databaseBuilder(get(), PokedexDatabase::class.java, "pokedex.db").build()}
    factory { get<PokedexDatabase>().pokemonDao() }
    factory { get<PokedexDatabase>().berryDao() }
    factory { get<PokedexDatabase>().BackPackDao() }
    factory { get<PokedexDatabase>().pokedexDao() }
}

val frameworkRetrofitModule = module {
    single { PokemonClient.instance }
    single { PokedexClient.instance }
    single { BerryClient.instance }
    single { BackPackItemClient.instance }
}

val frameworkPokemonModule = module {
    factoryOf(::PokemonRoomDataSource) bind PokemonLocalDataSource::class
    factoryOf(::PokemonServerDataSource) bind PokemonRemoteDataSource::class
}

val frameworkPokedexModule = module {
    factoryOf(::PokedexRoomDataSource) bind PokedexLocalDataSource::class
    factoryOf(::PokemonServerDataSource) bind PokemonRemoteDataSource::class
}

val frameworkBerryModule = module {
    factoryOf(::BerryRoomDataSource) bind BerryLocalDataSource::class
    factoryOf(::BerryServerDataSource) bind BerryRemoteDataSource::class
}

val frameworkBackPackItemModule = module {
    factoryOf(::BackPackRoomDataSource) bind BackPackLocalDataSource::class
    factoryOf(::BackPackServerDataSource) bind BackPackRemoteDataSource::class
}

val frameworkRegionModule = module {
    factoryOf(::PlayServicesLocationDataSource) bind LocationDataSource::class
    factory { LocationServices.getFusedLocationProviderClient(get<Context>())}
    factoryOf(::GeocoderRegionDataSource) bind RegionDataSource::class
    factory { Geocoder(get()) }
}

val frameWorkMappersModule = module {
    single { PokemonMapper() }
    single { BerryMapper() }
    single { ItemsMapper() }
}

val frameworkModules = listOf(
    frameworkRoomModule,
    frameworkRetrofitModule,
    frameworkPokemonModule,
    frameworkPokedexModule,
    frameworkBerryModule,
    frameworkBackPackItemModule,
    frameworkRegionModule,
    frameWorkMappersModule
)