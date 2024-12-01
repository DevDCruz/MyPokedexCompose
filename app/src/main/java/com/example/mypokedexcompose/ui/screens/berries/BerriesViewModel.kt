package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.data.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BerriesViewModel(
    getBerriesUseCase: com.example.mypokedexcompose.usecase.GetBerriesUseCase,
    private val fetchberryByNameUseCase: com.example.mypokedexcompose.usecase.FetchBerryByNameUseCase,
    private val fetchBerriesUseCase: com.example.mypokedexcompose.usecase.FetchBerriesUseCase
) : ViewModel() {

    val state: StateFlow<Result<List<BerryDomain>>> = getBerriesUseCase()
        .stateAsResultIn(viewModelScope)

    init {
        viewModelScope.launch {
            fetchAllBerries()
        }
    }

    private fun fetchAllBerries() {
        viewModelScope.launch {
            fetchBerriesUseCase()
        }
    }

    suspend fun fetchBerryDetails(name: String): BerryDomain? {
        val berryDetail = fetchberryByNameUseCase(name)
        return berryDetail.firstOrNull()
    }
}