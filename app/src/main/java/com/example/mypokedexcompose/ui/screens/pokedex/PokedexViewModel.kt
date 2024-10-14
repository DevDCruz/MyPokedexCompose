package com.example.mypokedexcompose.ui.screens.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.pokedex.PokedexRepository
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val repository = PokedexRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {

        val savedPokemons = savedStateHandle.get<List<Pokemon>>("pokemons")
        if (savedPokemons != null) {
            _state.value = UiState(
                pokemons = savedPokemons,
                loading = false
            )
        }
    }

    fun onUiReady() {
        val savedPokemons = savedStateHandle.get<List<Pokemon>>("pokemons")
        if (savedPokemons != null) {
            _state.value = UiState(pokemons = savedPokemons, loading = false)
        } else {
            viewModelScope.launch {
                _state.value = UiState(loading = true)
                val pokemons = repository.fetchRegionalPokedex(0, 1010)
                _state.value = UiState(pokemons = pokemons, loading = false)
                savedStateHandle["pokemons"] = pokemons
            }
        }
    }

    fun fetchPokemonsForRegion(range: IntArray) {
        viewModelScope.launch {
            _state.value = UiState(loading = true)

            val pokemons =
                repository.fetchRegionalPokedex(offset = range[0], limit = range[1] - range[0])

            _state.value = UiState(
                pokemons = pokemons,
                loading = false
            )
            savedStateHandle["pokemons"] = pokemons
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList()
    )
}