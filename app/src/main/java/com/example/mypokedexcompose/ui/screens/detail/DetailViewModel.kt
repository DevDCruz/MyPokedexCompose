package com.example.mypokedexcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.ifSuccess
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.usecase.FetchPokemonByNameUseCase
import com.example.mypokedexcompose.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    fetchPokemonByNameUseCase: com.example.mypokedexcompose.usecase.FetchPokemonByNameUseCase,
    private val toggleFavoriteUseCase: com.example.mypokedexcompose.usecase.ToggleFavoriteUseCase,
    name: String
) : ViewModel() {

    val state: StateFlow<Result<Pokemon>> = fetchPokemonByNameUseCase(name)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess { currentPokemon ->
            viewModelScope.launch {
                toggleFavoriteUseCase(currentPokemon)
            }
        }
    }
}

fun getPokemonType(pokemon: Pokemon): String {
    val types = pokemon.types ?: return "Unknown Type"
    return types.joinToString(" - ") { it.type.name.changefirstCharToUpperCase() }
}
