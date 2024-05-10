package com.example.mypokedexcompose.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.detail.pokemonresult.PokemonRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val name: String) : ViewModel() {

    private val repository = PokemonRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(

                pokemon = repository.fetchPokemon(name),
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/",
                loading = false
            )

        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null,
        val sprite: String? = null
    )
}