package com.example.mypokedexcompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.ifSuccess
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.ui.common.changefirstCharToUpperCase
import com.example.mypokedexcompose.usecase.FetchPokemonByNameUseCase
import com.example.mypokedexcompose.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    fetchPokemonByNameUseCase: FetchPokemonByNameUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    @Named("pokemonName") name: String
) : ViewModel() {

    val state: StateFlow<Result<PokemonDomain>> = fetchPokemonByNameUseCase(name)
        .stateAsResultIn(viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess { currentPokemon ->
            viewModelScope.launch {
                toggleFavoriteUseCase(currentPokemon)
            }
        }
    }
}

fun getPokemonType(pokemon: PokemonDomain): String {
    val types = pokemon.types ?: return "Unknown Type"
    return types.joinToString(" - ") { it.type.name.changefirstCharToUpperCase() }
}
