package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.dataSource.repository.BackPackItemRepository
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataRespositoryModule {

    @Binds
    abstract fun bindPokedexRepository(pokedexRepository: PokedexRepository): IPokedexRepository

    @Binds
    abstract fun bindPokemonRepository(pokemonRepository: PokemonRepository): IPokemonRepository

    @Binds
    abstract fun bindBerryRepository(berryRepository: BerryRepository): IBerryRepository

    @Binds
    abstract fun bindBackpackRepository(backPackItemRepository: BackPackItemRepository): IBackPackItemRepository
}