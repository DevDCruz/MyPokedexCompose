package com.example.mypokedexcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PokemonRepository,
    name: String
) : ViewModel() {

    val state: StateFlow<UiState> = repository.fetchPokemonDetails(name)
        .map { pokemon -> UiState(pokemon = pokemon) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState(loading = true)
        )

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null
    )

    fun onFavoriteClicked() {
        state.value.pokemon?.let { currentPokemon ->
            viewModelScope.launch {
                repository.toggleFavorite(currentPokemon)
            }
        }
    }
}

fun getPokemonType(pokemon: Pokemon): String {
    val types = pokemon.types ?: return "Unknown Type"
    return types.joinToString(" - ") { it.type.name.changefirstCharToUpperCase() }
}
