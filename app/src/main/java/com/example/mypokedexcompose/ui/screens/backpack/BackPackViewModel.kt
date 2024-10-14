package com.example.mypokedexcompose.ui.screens.backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.dataSource.repository.ItemRepository
import com.example.mypokedexcompose.data.items.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class BackPackViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fechAllItems()
        }
    }

    private suspend fun fechAllItems() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.fetchAllItems()

            repository.items.collect { items ->
                _state.value = UiState(items = items, loading = false)
            }
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