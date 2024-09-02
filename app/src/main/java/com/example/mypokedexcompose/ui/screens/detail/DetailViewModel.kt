package com.example.mypokedexcompose.ui.screens.detail

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.R
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.pokemon.PokemonRepository
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val name: String) : ViewModel() {

    private val repository = PokemonRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(

                pokemon = repository.fetchPokemon(name),
                loading = false
            )

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null
    )
}

fun getPokemonType(pokemon: Pokemon): String {
    return if (pokemon.types?.size!! > 1) {
        changefirstCharToUpperCase(pokemon.types[0].type.name) +
                " - " + changefirstCharToUpperCase(pokemon.types[1].type.name)

    } else {
        changefirstCharToUpperCase(pokemon.types[0].type.name)
    }
}