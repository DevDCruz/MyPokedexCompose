package com.example.mypokedexcompose.ui.screens.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.PokedexRepository
import com.example.mypokedexcompose.data.pokemon.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val repository = PokedexRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        val scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        _state.value = UiState(scrollPosition = scrollPosition)
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(
                pokemons = repository.fetchPokedex(),
                loading = false
            )
        }
    }

    fun savedScrollPosition(position: Int) {
        savedStateHandle["scroll_position"] = position
    }


    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList(),
        val scrollPosition: Int = 0
    )
}