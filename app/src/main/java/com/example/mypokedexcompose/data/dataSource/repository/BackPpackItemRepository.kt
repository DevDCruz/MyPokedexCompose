package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.ItemsMapper
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BackPpackItemRepository(
    private val backPackRemoteDataSource: BackPackRemoteDataSource,
    private val backPackLocalDataSource: BackPackLocalDataSource,
    private val itemMapper: ItemsMapper
) {

    val backPackItems: Flow<List<BackpackItem>> =
        backPackLocalDataSource.items.map { itemMapper.toDomainList(it) }

    suspend fun fetchBackPackItems() {
        if (backPackLocalDataSource.isEmpty()) {
            val remoteItems = backPackRemoteDataSource.fetchItems()
            val localItems = itemMapper.fromRemoteToEntityList(remoteItems)
            backPackLocalDataSource.saveItems(localItems)
        }
    }

    suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItem?> = flow {
        val localItem = backPackLocalDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.isDetailFetched) {
            Log.d("ItemRepository", "fetchItemById in local: ${localItem.name}")
            emit(itemMapper.toDomain(localItem))
        } else {
            val remoteItem = backPackRemoteDataSource.fetchItemByName(name)
            val newLocalITem = itemMapper.fromRemoteToEntity(remoteItem)
            Log.d("ItemRepository", "fetchItemById in remote: ${newLocalITem.name}")
            newLocalITem.isDetailFetched = true
            backPackLocalDataSource.saveItems(listOf(newLocalITem))
            emit(itemMapper.toDomain(newLocalITem))
        }

    }

}