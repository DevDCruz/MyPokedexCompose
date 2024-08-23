package com.example.mypokedexcompose.ui.screens.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedexcompose.data.items.Item
import com.example.mypokedexcompose.data.items.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BackPackViewModel : ViewModel() {

    private val repository = ItemRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(
                items = repository.fetchItems(),
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/",
                loading = false
            )
        }
    }

    suspend fun fetchItemDetails(id: Int): Item {
        return repository.fetchItemById(id)
    }

    data class UiState(
        val loading: Boolean = false,
        val items: List<Item> = emptyList(),
        val item: Item? = null,
        val sprite: String? = null
    )

}