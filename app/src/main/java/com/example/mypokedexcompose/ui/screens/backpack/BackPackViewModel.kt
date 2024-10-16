package com.example.mypokedexcompose.ui.screens.backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.dataSource.repository.ItemRepository
import com.example.mypokedexcompose.data.items.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BackPackViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    val state: StateFlow<UiState> = repository.items
        .map { items -> UiState(items = items) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState(loading = true)
        )

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

    data class UiState(
        val loading: Boolean = false,
        val items: List<Item> = emptyList(),
        val item: Item? = null
    )
}