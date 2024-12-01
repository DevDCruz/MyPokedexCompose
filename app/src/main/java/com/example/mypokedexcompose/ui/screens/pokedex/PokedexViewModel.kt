package com.example.mypokedexcompose.ui.screens.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.region.PokedexRegion
import com.example.mypokedexcompose.data.region.RegionMapper
import com.example.mypokedexcompose.data.region.RegionRepository
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class PokedexViewModel(
    val savedStateHandle: SavedStateHandle,
    private val regionRepository: RegionRepository,
    private val getchPokedexUseCase: com.example.mypokedexcompose.usecase.GetchPokedexUseCase,
    private val fetchPokedexUseCase: com.example.mypokedexcompose.usecase.FetchPokedexUseCase,
    private val fetchPokedexForRegionUseCase: com.example.mypokedexcompose.usecase.FetchPokedexForRegionUseCase,
    private val regionMapper: RegionMapper
) : ViewModel() {

    val onUiReady = MutableStateFlow(false)
    private val selectedRegionFlow: StateFlow<PokedexRegion> =
        savedStateHandle.getStateFlow("selected_region", PokedexRegion.ALL_GENERATIONS)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<UiState>> = onUiReady
        .filter { it }
        .flatMapLatest { getchPokedexUseCase() }
        .combine(selectedRegionFlow) { pokemons, selectedPokedexRegion ->
            val filteredPokemons =
                pokemons.filter { it.id in selectedPokedexRegion.range[0]..selectedPokedexRegion.range[1] }
            UiState(filteredPokemons, selectedPokedexRegion)
        }.stateAsResultIn(viewModelScope)

    fun fetchAllPokemons() {
        viewModelScope.launch {
            fetchPokedexUseCase()
        }
    }

    fun fetchPokemonsForRegion(range: IntArray) {
        viewModelScope.launch {
            val pokemons =
                fetchPokedexForRegionUseCase(range)
            savedStateHandle["pokemons"] = pokemons
        }
    }

    suspend fun updateRegionBasedOnLocation(pokedexState: PokedexState) {
        val region = regionMapper.mapLocationtoPokedexRegion(regionRepository.findLastRegion())
        fetchPokemonsForRegion(region.range)
        pokedexState.updateSelectedGeneration(region)
    }

    data class UiState(
        val pokemons: List<PokemonDomain>? = null,
        val selectedPokedexRegion: PokedexRegion = PokedexRegion.ALL_GENERATIONS
    )
}