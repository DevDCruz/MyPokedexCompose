package com.example.mypokedexcompose.ui.screens.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.data.pokemon.Pokemon
import com.example.mypokedexcompose.data.region.RegionMapper
import com.example.mypokedexcompose.data.region.RegionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(
    val savedStateHandle: SavedStateHandle,
    private val regionRepository: RegionRepository,
    private val repository: PokedexRepository,
    private val regionMapper: RegionMapper
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()


    init {
        viewModelScope.launch {
            fetchAllPokemons()
        }
    }

    private fun fetchAllPokemons() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchAllPokemons()

            repository.pokemons.collect { pokemons ->
                _state.value = UiState(pokemons = pokemons, loading = false)
                savedStateHandle["pokemons"] = pokemons
            }
        }
    }

    fun fetchPokemonsForRegion(range: IntArray) {
        viewModelScope.launch {
            _state.value = UiState(loading = true)

            val pokemons =
                repository.fetchRegionalPokedex(offset = range[0], limit = range[1] - range[0])

            _state.value = UiState(
                pokemons = pokemons,
                loading = false
            )
            savedStateHandle["pokemons"] = pokemons
        }
    }

    suspend fun updateRegionBasedOnLocation(pokedexState: PokedexState) {
        val region = regionMapper.mapLocationtoPokedexRegion(regionRepository.findLastRegion())
        fetchPokemonsForRegion(region.range)
        pokedexState.updateSelectedGeneration(region)
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList()
    )
}