package com.example.mypokedexcompose.ui.screens.backpack

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.usecase.FetchBackPackItemByNameUseCase
import com.example.mypokedexcompose.usecase.FetchBackpackItemsUseCase
import com.example.mypokedexcompose.usecase.GetBackPackItemsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class BackPackViewModel(
    getBackPackItemsUseCase: GetBackPackItemsUseCase,
    private val fetchBackPackItemByNameUseCase: FetchBackPackItemByNameUseCase,
    private val fetchBackpackItemsUseCase: FetchBackpackItemsUseCase
) : ViewModel() {

    val state: StateFlow<com.example.mypokedexcompose.data.Result<List<BackpackItemDomain>>> = getBackPackItemsUseCase()
        .stateAsResultIn(viewModelScope)

    init {
        viewModelScope.launch {
            fechAllItems()
        }
    }

    private fun fechAllItems() {
        viewModelScope.launch {
            fetchBackpackItemsUseCase()
            Log.d("BackPackViewModel", "fetching All Items")
        }
    }

    suspend fun fetchItemDetails(name: String): BackpackItemDomain? {
        val itemDetail = fetchBackPackItemByNameUseCase(name)
        return itemDetail.firstOrNull()
    }
}