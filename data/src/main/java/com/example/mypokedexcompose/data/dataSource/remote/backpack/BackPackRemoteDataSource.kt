package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

interface BackPackRemoteDataSource {
    suspend fun fetchItems(): List<BackpackItem>

    suspend fun fetchItemByName(name: String): BackpackItem
}
