package com.example.mypokedexcompose.framework.remote.backpack

import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.framework.mappers.ItemsMapper
import javax.inject.Inject

internal class BackPackServerDataSource @Inject constructor(
    private val backpackItemService: BackpackItemService,
    private val itemsMapper: ItemsMapper
) : BackPackRemoteDataSource {

    override suspend fun fetchItems(): List<BackpackItemDomain> {
        val response = backpackItemService.fetchItems(304)
        return response.results.map { items -> itemsMapper.fromRemoteToDomain(items) }
    }

    override suspend fun fetchItemByName(name: String): BackpackItemDomain = backpackItemService
        .getItemByName(name)
        .let { itemsMapper.fromRemoteToDomain(it) }
}