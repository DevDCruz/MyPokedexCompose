package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BerriesViewModel(
    private val repository: BerryRepository
) : ViewModel() {

    val state: StateFlow<UiState> = repository.berries
        .map { berries -> UiState(berries = berries) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState(loading = true)
        )

    init {
        viewModelScope.launch {
            fetchAllBerries()
        }
    }

    private suspend fun fetchAllBerries() {
        viewModelScope.launch {
            repository.fetchAllBerries()

            repository.berries.collect { berries ->
                UiState(berries = berries, loading = false)
            }
        }
    }

    suspend fun fetchBerryDetails(name: String): Berry? {
        val berryDetail = repository.fetchBerryByName(name)
        return berryDetail.firstOrNull()
    }

    data class UiState(
        val loading: Boolean = false,
        val berries: List<Berry> = emptyList(),
        val berry: Berry? = null
    )
}