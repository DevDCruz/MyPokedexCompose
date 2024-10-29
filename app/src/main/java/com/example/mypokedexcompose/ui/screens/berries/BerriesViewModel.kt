package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.domaindatalayer.berries.Berry
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.usecases.FetchBerriesUseCase
import com.example.mypokedexcompose.usecases.FetchBerryByNameUseCase
import com.example.mypokedexcompose.usecases.GetchBerriesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BerriesViewModel(
    getchBerriesUseCase: GetchBerriesUseCase,
    private val fetchberryByNameUseCase: FetchBerryByNameUseCase,
    private val fetchBerriesUseCase: FetchBerriesUseCase
) : ViewModel() {

    val state: StateFlow<Result<List<Berry>>> = getchBerriesUseCase()
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

    suspend fun fetchBerryDetails(name: String): Berry? {
        val berryDetail = fetchberryByNameUseCase(name)
        return berryDetail.firstOrNull()
    }
}