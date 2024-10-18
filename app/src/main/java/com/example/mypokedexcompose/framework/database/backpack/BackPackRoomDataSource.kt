package com.example.mypokedexcompose.framework.database.backpack

import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.data.dataSource.local.backpack.ItemEntity

class BackPackRoomDataSource(private val dao: BackPackDao) : BackPackLocalDataSource {

    override val items = dao.fetchItems()
    override fun getItemByName(name: String) = dao.fetchItemByName(name)
    override suspend fun isEmpty() = dao.countItems() == 0
    override suspend fun saveItems(itemEntity: List<ItemEntity>) = dao.saveItem(itemEntity)
}