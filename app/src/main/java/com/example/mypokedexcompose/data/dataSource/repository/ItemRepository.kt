package com.example.mypokedexcompose.data.dataSource.repository

import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.database.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.ItemsMapper
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.data.items.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ItemRepository(
    private val backPackRemoteDataSource: BackPackRemoteDataSource,
    private val backPackLocalDataSource: BackPackLocalDataSource,
    private val itemMapper: ItemsMapper
) {

    val items: Flow<List<Item>> = backPackLocalDataSource.items.map { itemMapper.toDomainList(it) }

    suspend fun fetchAllItems(){
        if (backPackLocalDataSource.isEmpty()) {
            val remoteItems = backPackRemoteDataSource.fetchItems()
            val localItems = itemMapper.fromRemoteToEntityList(remoteItems)
            backPackLocalDataSource.saveItems(localItems)
        }
    }

    suspend fun fetchItemByName(name : String): Flow<Item?> = flow {
        val localItem = backPackLocalDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.isDetailFetched) {
            Log.d("ItemRepository", "fetchItemById in local: $localItem")
            emit(itemMapper.toDomain(localItem))
        } else{
            val remoteItem = backPackRemoteDataSource.fetchItemByName(name)
            val newLocalITem = itemMapper.fromRemoteToEntity(remoteItem)
            Log.d("ItemRepository", "fetchItemById in remote: $newLocalITem")
            newLocalITem.isDetailFetched = true
            backPackLocalDataSource.saveItems(listOf(newLocalITem))
            emit(itemMapper.toDomain(newLocalITem))
        }

    }

}