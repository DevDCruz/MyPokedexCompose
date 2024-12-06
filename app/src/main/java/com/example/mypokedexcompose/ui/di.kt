package com.example.mypokedexcompose.ui

import androidx.lifecycle.SavedStateHandle
import com.example.mypokedexcompose.ui.screens.NavArs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {
    @Provides
    @ViewModelScoped
    @Named("pokemonName")
    fun providePokemonId(savedStateHandle: SavedStateHandle): String {
        return savedStateHandle[NavArs.POKEMON_NAME.toString()] ?: ""

    }
}