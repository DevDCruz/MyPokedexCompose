package com.example.mypokedexcompose.ui.screens.backpack

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.data.stateAsResultIn
import com.example.mypokedexcompose.usecases.FetchBackPackItemByNameUseCase
import com.example.mypokedexcompose.usecases.FetchBackpackItemsUseCase
import com.example.mypokedexcompose.usecases.GetBackPackItemsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BackPackViewModel(
    getBackPackItemsUseCase: GetBackPackItemsUseCase,
    private val fetchBackPackItemByNameUseCase: FetchBackPackItemByNameUseCase,
    private val fetchBackpackItemsUseCase: FetchBackpackItemsUseCase
) : ViewModel() {

    val state: StateFlow<Result<List<BackpackItem>>> = getBackPackItemsUseCase()
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

    suspend fun fetchItemDetails(name: String): BackpackItem? {
        val itemDetail = fetchBackPackItemByNameUseCase(name)
        return itemDetail.firstOrNull()
    }
}