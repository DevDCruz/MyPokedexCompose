package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.data.berries.BerryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BerriesViewModel : ViewModel() {

    private val repository = BerryRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(
                berries = repository.fetchBerries(),
                loading = false
            )
        }
    }

    suspend fun fetchBerryDetails(name: String): Berry {
        return repository.fetchBerryByName(name)
    }

    data class UiState(
        val loading: Boolean = false,
        val berries: List<Berry> = emptyList(),
        val berry: Berry? = null
    )
}