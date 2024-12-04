package com.example.mypokedexcompose.usecase

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCasePokemonModule = module {
    factoryOf(::FetchPokemonByNameUseCase)
    factoryOf(::FetchRandomPokemonUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}

val useCasePokedexModule = module {
    factoryOf(::FetchPokedexUseCase)
    factoryOf(::FetchPokedexForRegionUseCase)
    factoryOf(::GetPokedexUseCase)
}

val useCaseBerryModule = module {
    factoryOf(::FetchBerriesUseCase)
    factoryOf(::GetBerriesUseCase)
    factoryOf(::FetchBerryByNameUseCase)
}

val useCaseBackPackItemModule = module {
    factoryOf(::FetchBackpackItemsUseCase)
    factoryOf(::GetBackPackItemsUseCase)
    factoryOf(::FetchBackPackItemByNameUseCase)
}

val useCaseModules = listOf(
    useCasePokemonModule,
    useCasePokedexModule,
    useCaseBerryModule,
    useCaseBackPackItemModule
)