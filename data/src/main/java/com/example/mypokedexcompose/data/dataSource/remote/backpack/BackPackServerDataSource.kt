package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

interface BackPackServerDataSource {
    suspend fun fetchItems(): List<ItemResult>

    suspend fun fetchItemByName(name: String): BackpackItem
}
