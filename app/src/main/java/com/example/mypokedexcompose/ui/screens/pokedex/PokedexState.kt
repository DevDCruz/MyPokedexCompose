package com.example.mypokedexcompose.ui.screens.pokedex

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import com.example.mypokedexcompose.data.region.PokedexRegion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokedexState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    private val savedStateHandle: SavedStateHandle
) {

    private val _selectedPokedexRegion = MutableStateFlow(PokedexRegion.ALL_GENERATIONS)
    val selectedPokedexRegion: StateFlow<PokedexRegion> = _selectedPokedexRegion

    var scrollPosition: Int = 0

    init {
        scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        val savedRegion = savedStateHandle.get<PokedexRegion>("selected_region")
        if (savedRegion != null) {
            _selectedPokedexRegion.value = savedRegion
        } else {
            _selectedPokedexRegion.value = PokedexRegion.ALL_GENERATIONS
        }
    }

    fun updateSelectedGeneration(pokedexRegion: PokedexRegion) {
        _selectedPokedexRegion.value = pokedexRegion
        savedStateHandle["selected_region"] = pokedexRegion
    }

    fun onClikedRegion(pokedexRegion: PokedexRegion, viewModel: PokedexViewModel) {
        updateSelectedGeneration(pokedexRegion)
        viewModel.fetchPokemonsForRegion(pokedexRegion.range)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberPokedexState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    savedStateHandle: SavedStateHandle,
    viewModel: PokedexViewModel
): PokedexState {

    val pokedexState = remember(scrollBehavior) {
        PokedexState(
            scrollBehavior,
            savedStateHandle
        )
    }

    LaunchedEffect(pokedexState.selectedPokedexRegion.collectAsState().value) {
        viewModel.fetchPokemonsForRegion(pokedexState.selectedPokedexRegion.value.range)
    }
    return pokedexState
}

