package com.example.mypokedexcompose.ui.screens.pokedex

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import com.example.mypokedexcompose.data.pokedex.Region
import com.example.mypokedexcompose.ui.common.PermissionRequestEffect
import com.example.mypokedexcompose.ui.common.getRegion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    private val savedStateHandle: SavedStateHandle
) {

    private val _selectedRegion = MutableStateFlow(Region.ALL_GENERATIONS)
    val selectedRegion: StateFlow<Region> = _selectedRegion

    var scrollPosition: Int = 0

    init {
        scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        _selectedRegion.value =
            savedStateHandle.get<Region>("selected_region") ?: Region.ALL_GENERATIONS
    }

    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit) {
        val ctx = LocalContext.current.applicationContext
        val coroutineScope = rememberCoroutineScope()

        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
            coroutineScope.launch {
                val region = ctx.getRegion()
                onRegion(region)
            }
        }
    }

    fun savedScrollPosition(position: Int) {
        scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        savedStateHandle["scroll_position"] = position
    }

    fun updateSelectedGeneration(region: Region) {
        _selectedRegion.value = region
        savedStateHandle["selected_region"] = region
    }

    fun onClikedRegion(region: Region, viewModel: PokedexViewModel) {
        viewModel.fetchPokemonsForRegion(region.range)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberPokedexState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    savedStateHandle: SavedStateHandle,
    viewModel: PokedexViewModel
): PokedexState {

    val pokedexState = remember(scrollBehavior) { PokedexState(scrollBehavior, savedStateHandle) }

    LaunchedEffect(pokedexState.selectedRegion.collectAsState().value) {
        viewModel.fetchPokemonsForRegion(pokedexState.selectedRegion.value.range)
    }
    return pokedexState
}

