package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domaindatalayer.backpackItems.BackpackItem

interface BackPackRemoteDataSource {
    suspend fun fetchItems(): List<ItemResult>

    suspend fun fetchItemByName(name: String): BackpackItem
}
