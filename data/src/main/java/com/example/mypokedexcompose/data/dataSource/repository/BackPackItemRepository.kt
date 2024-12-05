package com.example.mypokedexcompose.data.dataSource.repository


import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.remote.backpack.BackPackRemoteDataSource
import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class BackPackItemRepository(
    private val backPackServerDataSource: BackPackRemoteDataSource,
    private val backPackLocalDataSource: BackPackLocalDataSource
) : IBackPackItemRepository {

    override val backPackItems: Flow<List<BackpackItemDomain>> =
        backPackLocalDataSource.items

    override suspend fun fetchBackPackItems() {
        if (backPackLocalDataSource.isNotFetched()) {
            val remoteItems = backPackServerDataSource.fetchItems()
            backPackLocalDataSource.saveItems(remoteItems)
        }
    }

    override suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItemDomain?> = flow {
        val localItem = backPackLocalDataSource.getItemByName(name).firstOrNull()
        if (localItem != null && localItem.detailFetched) {
            emit(localItem)
        } else {
            val remoteItem = backPackServerDataSource.fetchItemByName(name)
            remoteItem.detailFetched = true
            backPackLocalDataSource.saveItems(listOf(remoteItem))
            emit(remoteItem)
        }
    }
}