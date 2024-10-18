package com.example.mypokedexcompose.data.dataSource.local.backpack

import kotlinx.coroutines.flow.Flow

interface BackPackLocalDataSource {
    val items: Flow<List<ItemEntity>>
    fun getItemByName(name: String): Flow<ItemEntity?>

    suspend fun isEmpty(): Boolean

    suspend fun saveItems(itemEntity: List<ItemEntity>)
}

class BackPackRoomDataSource(private val dao: BackPackDao) : BackPackLocalDataSource {

    override val items = dao.fetchItems()
    override fun getItemByName(name: String) = dao.fetchItemByName(name)
    override suspend fun isEmpty() = dao.countItems() == 0
    override suspend fun saveItems(itemEntity: List<ItemEntity>) = dao.saveItem(itemEntity)
}