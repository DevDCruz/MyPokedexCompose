package com.example.mypokedexcompose.ui.screens.berries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.usecase.FetchBerriesUseCase
import com.example.mypokedexcompose.usecase.FetchBerryByNameUseCase
import com.example.mypokedexcompose.usecase.GetBerriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerriesViewModel @Inject constructor(
    getBerriesUseCase: GetBerriesUseCase,
    private val fetchberryByNameUseCase: FetchBerryByNameUseCase,
    private val fetchBerriesUseCase: FetchBerriesUseCase
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