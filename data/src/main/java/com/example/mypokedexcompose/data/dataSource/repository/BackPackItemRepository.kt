package com.example.mypokedexcompose.data.dataSource.repository


import android.util.Log
import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class BackPackItemRepository(
    private val backPackServerDataSource: BackPackRemoteDataSource,
    private val backPackLocalDataSource: BackPackLocalDataSource
) : IBackPackItemRepository {

    override val backPackItems: Flow<List<BackpackItem>> =
        backPackLocalDataSource.items

    override suspend fun fetchBackPackItems() {
        if (backPackLocalDataSource.isNotFetched()) {
            val remoteItems = backPackServerDataSource.fetchItems()
            backPackLocalDataSource.saveItems(remoteItems)
        }
    }

    override suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItem?> = flow {
        val localItem = backPackLocalDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.detailFetched) {
            Log.d("BackPackItemRepository", "Item ${localItem.name} fetched from Local")
            emit(localItem)
        } else {
            val remoteItem = backPackServerDataSource.fetchItemByName(name)
            Log.d("BackPackItemRepository", "Item ${remoteItem.name} fetched from server")

            remoteItem.detailFetched = true
            backPackLocalDataSource.saveItems(listOf(remoteItem))
            emit(remoteItem)
        }
    }
}