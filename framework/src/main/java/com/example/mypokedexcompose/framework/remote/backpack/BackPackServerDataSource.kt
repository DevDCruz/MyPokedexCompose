package com.example.mypokedexcompose.framework.remote.backpack

import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.framework.database.backpack.ItemsMapper

class BackPackServerDataSource(
    private val itemClient: ItemClient,
    private val itemsMapper: ItemsMapper
) : BackPackRemoteDataSource {

    override suspend fun fetchItems(): List<BackpackItem> {
        val response = itemClient.instance.fetchItems(304)
        return response.results.map { items -> itemsMapper.fromRemoteToDomain(items) }
    }

    override suspend fun fetchItemByName(name: String): BackpackItem = itemClient.instance
        .getItemByName(name)
        .let { itemsMapper.fromRemoteToDomain(it) }
}