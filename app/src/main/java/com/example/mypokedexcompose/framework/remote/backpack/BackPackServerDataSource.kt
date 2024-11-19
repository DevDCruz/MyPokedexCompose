package com.example.mypokedexcompose.framework.remote.backpack

import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackServerDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.ItemResult
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem

class BackPackServerDataSource(
    private val itemClient: ItemClient
) : BackPackServerDataSource {

    override suspend fun fetchItems(): List<ItemResult> = itemClient.instance
        .fetchItems(304)
        .results

    override suspend fun fetchItemByName(name: String): BackpackItem = itemClient.instance
        .getItemByName(name)
}