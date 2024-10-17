package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.data.berries.Berry
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BerriesViewModel(
    private val repository: BerryRepository
) : ViewModel() {

    val state: StateFlow<Result<List<Berry>>> = repository.berries
        .stateAsResultIn(viewModelScope)

    init {
        viewModelScope.launch {
            fetchAllBerries()
        }
    }

    private suspend fun fetchAllBerries() {
        viewModelScope.launch {
            repository.fetchAllBerries()
        }
    }

    suspend fun fetchBerryDetails(name: String): Berry? {
        val berryDetail = repository.fetchBerryByName(name)
        return berryDetail.firstOrNull()
    }
}