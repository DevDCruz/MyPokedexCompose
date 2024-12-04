package com.example.mypokedexcompose

import com.example.mypokedexcompose.ui.screens.backpack.BackPackViewModel
import com.example.mypokedexcompose.ui.screens.berries.BerriesViewModel
import com.example.mypokedexcompose.ui.screens.detail.DetailViewModel
import com.example.mypokedexcompose.ui.screens.home.HomeViewModel
import com.example.mypokedexcompose.ui.screens.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelPokedexModule = module {
    viewModelOf(::PokedexViewModel)
}

val viewModelHomeModule = module {
    viewModelOf(::HomeViewModel)
}

val viewModelDetailModule = module {
    viewModelOf(::DetailViewModel)
}

val viewModelBackPackModule = module {
    viewModelOf(::BackPackViewModel)
}

val viewModelBerryModule = module {
    viewModelOf(::BerriesViewModel)
}

val viewModelModules = listOf(
    viewModelPokedexModule,
    viewModelHomeModule,
    viewModelDetailModule,
    viewModelBackPackModule,
    viewModelBerryModule
)