package com.example.mypokedexcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonRepository
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PokemonRepository,
    private val name: String
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchPokemon(name).collect { pokemon ->
                _state.value = UiState(
                    pokemon = pokemon,
                    loading = false
                )
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null)



    fun onFavoriteClicked() {
        state.value.pokemon?.let {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }
        }
    }
}
    fun getPokemonType(pokemon: Pokemon): String {

        val types = pokemon.types ?: return "Unknown Type"

        return types.joinToString(" - ") { it.type.name.changefirstCharToUpperCase() }
    }
