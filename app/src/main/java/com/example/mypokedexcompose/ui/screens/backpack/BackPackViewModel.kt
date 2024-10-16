package com.example.mypokedexcompose.ui.screens.backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.Result
import com.example.mypokedexcompose.data.StateAsResultIn
import com.example.mypokedexcompose.data.dataSource.repository.ItemRepository
import com.example.mypokedexcompose.data.items.Item
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BackPackViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    val state: StateFlow<Result<List<Item>>> = repository.items
        .StateAsResultIn(viewModelScope)

    init {
        viewModelScope.launch {
            fechAllItems()
        }
    }

    private suspend fun fechAllItems() {
        viewModelScope.launch {
            repository.fetchAllItems()
        }
    }

    suspend fun fetchItemDetails(name: String): Item? {
        val itemDetail = repository.fetchItemByName(name)
        return itemDetail.firstOrNull()
    }
}