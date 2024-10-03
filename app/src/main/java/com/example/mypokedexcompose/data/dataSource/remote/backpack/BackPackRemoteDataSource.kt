package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.data.items.Item

class BackPackRemoteDataSource {

    suspend fun fetchItems(): List<ItemResult> = ItemClient.instance
        .fetchItems(304)
        .results

    suspend fun fetchItemById(id: Int): Item = ItemClient.instance
        .getItemById(id)

    suspend fun fetchItemByName(name: String): Item = ItemClient.instance
        .getItemByName(name)

}