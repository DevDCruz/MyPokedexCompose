package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

class BackPackRemoteDataSource(
    private val itemClient: ItemClient
) {

    suspend fun fetchItems(): List<ItemResult> = itemClient.instance
        .fetchItems(304)
        .results

    suspend fun fetchItemByName(name: String): BackpackItem = itemClient.instance
        .getItemByName(name)

}