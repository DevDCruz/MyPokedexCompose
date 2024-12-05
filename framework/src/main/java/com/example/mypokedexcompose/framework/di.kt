package com.example.mypokedexcompose.framework

import android.content.Context
import android.location.Geocoder
import androidx.room.Room
import com.example.mypokedexcompose.framework.database.PokedexDatabase
import com.example.mypokedexcompose.framework.mappers.BerryMapper
import com.example.mypokedexcompose.framework.mappers.ItemsMapper
import com.example.mypokedexcompose.framework.mappers.PokemonMapper
import com.example.mypokedexcompose.framework.mappers.RegionMapper
import com.example.mypokedexcompose.framework.remote.backpack.BackpackItemService
import com.example.mypokedexcompose.framework.remote.berries.BerryService
import com.example.mypokedexcompose.framework.remote.pokedex.PokedexService
import com.example.mypokedexcompose.framework.remote.pokemon.PokemonService
import com.google.android.gms.location.LocationServices
import okhttp3.OkHttpClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@ComponentScan
class FrameworkDataSourcesModule

val frameworkRoomModule = module {
    single { Room.databaseBuilder(get(), PokedexDatabase::class.java, "pokedex.db").build() }
    factory { get<PokedexDatabase>().pokemonDao() }
    factory { get<PokedexDatabase>().berryDao() }
    factory { get<PokedexDatabase>().BackPackDao() }
    factory { get<PokedexDatabase>().pokedexDao() }
}

val frameworkRetrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(PokemonService::class.java) }
    single { get<Retrofit>().create(BerryService::class.java) }
    single { get<Retrofit>().create(PokedexService::class.java) }
    single { get<Retrofit>().create(BackpackItemService::class.java) }
}

val frameworkRegionModule = module {
    factory { LocationServices.getFusedLocationProviderClient(get<Context>()) }
    factory { Geocoder(get()) }
}

val frameWorkMappersModule = module {
    single { PokemonMapper() }
    single { ItemsMapper() }
    single { BerryMapper() }
    single { RegionMapper() }
}

