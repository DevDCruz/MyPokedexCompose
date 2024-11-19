package com.example.mypokedexcompose.data.dataSource.repository


import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.mappers.ItemsMapper
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BackPackItemRepository(
    private val backPackServerDataSource: BackPackRemoteDataSource,
    private val backPackLocalDataSource: BackPackLocalDataSource,
    private val itemMapper: ItemsMapper
) : IBackPackItemRepository {

    override val backPackItems: Flow<List<BackpackItem>> =
        backPackLocalDataSource.items.map { itemMapper.toDomainList(it) }

    override suspend fun fetchBackPackItems() {
        if (backPackLocalDataSource.isEmpty()) {
            val remoteItems = backPackServerDataSource.fetchItems()
            val localItems = itemMapper.fromRemoteToEntityList(remoteItems)

            backPackLocalDataSource.saveItems(localItems)
        }
    }

    override suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItem?> = flow {
        val localItem = backPackLocalDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.isDetailFetched) {
            Log.d("BackPackItemRepository", "Item ${localItem.name} fetched from Local", )
            emit(itemMapper.toDomain(localItem))
        } else {
            val remoteItem = backPackServerDataSource.fetchItemByName(name)
            Log.d("BackPackItemRepository", "Item ${remoteItem.name} fetched from server", )
            val newLocalITem = itemMapper.fromRemoteToEntity(remoteItem)
            newLocalITem.isDetailFetched = true
            backPackLocalDataSource.saveItems(listOf(newLocalITem))
            emit(itemMapper.toDomain(newLocalITem))
        }

    }

}