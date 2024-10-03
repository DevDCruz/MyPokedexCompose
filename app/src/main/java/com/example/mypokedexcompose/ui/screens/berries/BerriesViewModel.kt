package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.data.berries.BerryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BerriesViewModel(
    private val repository: BerryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAllBerries()
        }
    }

    private suspend fun fetchAllBerries() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchAllBerries()

            repository.berries.collect { berries ->
                _state.value = UiState(berries = berries, loading = false)
            }
        }
    }

    suspend fun fetchBerryDetails(name: String) : Berry? {
        val berryDetail = repository.fetchBerryByName(name)
        return berryDetail.firstOrNull()
        }

    data class UiState(
        val loading: Boolean = false,
        val berries: List<Berry> = emptyList(),
        val berry: Berry? = null
    )
}