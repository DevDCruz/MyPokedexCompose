package com.example.mypokedexcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.data.ifSuccess
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PokemonRepository,
    name: String
) : ViewModel() {

    val state: StateFlow<Result<Pokemon>> = repository.fetchPokemonDetails(name)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess { currentPokemon ->
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
