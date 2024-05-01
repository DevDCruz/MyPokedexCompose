package com.example.mypokedexcompose.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.PokemonRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set
    private val repository = PokemonRepository()
    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(false, pokemons = repository.fetchPokedex())
        }

    }

    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList()
    )

}