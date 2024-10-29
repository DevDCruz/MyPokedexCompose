package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.mappers.ItemsMapper
import com.example.mypokedexcompose.domaindatalayer.backpackItems.BackpackItem
import com.example.mypokedexcompose.framework.database.backpack.BackPackRoomDataSource
import com.example.mypokedexcompose.framework.remote.backpack.BackPackServerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BackPpackItemRepository(
    private val backPackServerDataSource: BackPackServerDataSource,
    private val backPackRoomDataSource: BackPackRoomDataSource,
    private val itemMapper: ItemsMapper
) {

    val backPackItems: Flow<List<BackpackItem>> =
        backPackRoomDataSource.items.map { itemMapper.toDomainList(it) }

    suspend fun fetchBackPackItems() {
        if (backPackRoomDataSource.isEmpty()) {
            val remoteItems = backPackServerDataSource.fetchItems()
            val localItems = itemMapper.fromRemoteToEntityList(remoteItems)
            backPackRoomDataSource.saveItems(localItems)
        }
    }

    suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItem?> = flow {
        val localItem = backPackRoomDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.isDetailFetched) {
            Log.d("ItemRepository", "fetchItemById in local: ${localItem.name}")
            emit(itemMapper.toDomain(localItem))
        } else {
            val remoteItem = backPackServerDataSource.fetchItemByName(name)
            val newLocalITem = itemMapper.fromRemoteToEntity(remoteItem)
            Log.d("ItemRepository", "fetchItemById in remote: ${newLocalITem.name}")
            newLocalITem.isDetailFetched = true
            backPackRoomDataSource.saveItems(listOf(newLocalITem))
            emit(itemMapper.toDomain(newLocalITem))
        }

    }

}