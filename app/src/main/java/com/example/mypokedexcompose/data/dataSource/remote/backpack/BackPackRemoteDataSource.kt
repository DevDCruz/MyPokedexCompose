package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

class BackPackRemoteDataSource {

    suspend fun fetchItems(): List<ItemResult> = ItemClient.instance
        .fetchItems(304)
        .results

    suspend fun fetchItemById(id: Int): BackpackItem = ItemClient.instance
        .getItemById(id)

    suspend fun fetchItemByName(name: String): BackpackItem = ItemClient.instance
        .getItemByName(name)

}