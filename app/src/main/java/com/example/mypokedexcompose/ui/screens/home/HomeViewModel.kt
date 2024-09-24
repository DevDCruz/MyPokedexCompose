package com.example.mypokedexcompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PokemonRepository
) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchRandomPokemon().take(1).collect { pokemon ->
                _state.value = UiState(
                    pokemon = pokemon,
                    loading = false
                )

            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null
    )
}