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
import androidx.lifecycle.SavedStateHandle
import com.example.mypokedexcompose.data.pokedex.PokedexRegion
import com.example.mypokedexcompose.data.region.RegionRepository
import com.example.mypokedexcompose.ui.common.PermissionRequestEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
    private val savedStateHandle: SavedStateHandle,
    private val regionRepository: RegionRepository
) {

    private val _selectedPokedexRegion = MutableStateFlow(PokedexRegion.ALL_GENERATIONS)
    val selectedPokedexRegion: StateFlow<PokedexRegion> = _selectedPokedexRegion

    var scrollPosition: Int = 0

    init {
        scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        _selectedPokedexRegion.value =
            savedStateHandle.get<PokedexRegion>("selected_region") ?: PokedexRegion.ALL_GENERATIONS
    }

    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit) {
        val coroutineScope = rememberCoroutineScope()

        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {
            coroutineScope.launch {
                val location = regionRepository.findLastregion()
                val region = mapLocationtoPokedexRegion(location)
                onRegion(region.displayName)
            }
        }
    }

    fun savedScrollPosition(position: Int) {
        scrollPosition = savedStateHandle.get<Int>("scroll_position") ?: 0
        savedStateHandle["scroll_position"] = position
    }

    fun updateSelectedGeneration(pokedexRegion: PokedexRegion) {
        _selectedPokedexRegion.value = pokedexRegion
        savedStateHandle["selected_region"] = pokedexRegion
    }

    fun onClikedRegion(pokedexRegion: PokedexRegion, viewModel: PokedexViewModel) {
        viewModel.fetchPokemonsForRegion(pokedexRegion.range)
    }

    fun updateRegionBasedOnLocation(location: String) {
        val region = mapLocationtoPokedexRegion(location)
        updateSelectedGeneration(region)
    }

    fun mapLocationtoPokedexRegion(location: String): PokedexRegion {
        return when (location) {
            "CN" -> PokedexRegion.GENERATION_1
            "JP" -> PokedexRegion.GENERATION_2
            "KR" -> PokedexRegion.GENERATION_3
            "IN" -> PokedexRegion.GENERATION_4
            "US" -> PokedexRegion.GENERATION_5
            "FR" -> PokedexRegion.GENERATION_6
            "AU" -> PokedexRegion.GENERATION_7
            "UK" -> PokedexRegion.GENERATION_8
            "ES" -> PokedexRegion.GENERATION_9
            else -> PokedexRegion.ALL_GENERATIONS
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberPokedexState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    savedStateHandle: SavedStateHandle,
    viewModel: PokedexViewModel,
    regionRepository: RegionRepository
): PokedexState {

    val pokedexState = remember(scrollBehavior) {
        PokedexState(
            scrollBehavior,
            savedStateHandle,
            regionRepository
        )
    }

    LaunchedEffect(pokedexState.selectedPokedexRegion.collectAsState().value) {
        viewModel.fetchPokemonsForRegion(pokedexState.selectedPokedexRegion.value.range)
    }
    return pokedexState
}

