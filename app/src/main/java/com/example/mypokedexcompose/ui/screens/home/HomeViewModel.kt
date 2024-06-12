package com.example.mypokedexcompose.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Pokemon
import com.example.mypokedexcompose.data.PokedexRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set
    private val repository = PokedexRepository()

    init {
        val scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        state = state.copy(scrollPosition = scrollPosition)
    }
    fun onUiReady() {
        viewModelScope.launch {
            state = state.copy(loading = true)
            state = state.copy(
                pokemons = repository.fetchPokedex(),
                spritePokedex = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/",
                loading = false

            )
        }

    }

fun savedScrollPosition(position: Int){
    savedStateHandle.set("scroll_position", position)
}


    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList(),
        val spritePokedex: String? = null,
        val scrollPosition: Int = 0
    )

}