package com.example.mypokedexcompose.data.dataSource.local.backpack

class BackPackLocalDataSource(private val dao: BackPackDao) {

    val items = dao.fetchItems()
    fun getItemByName(name: String) = dao.fetchItemByName(name)
    suspend fun isEmpty() = dao.countItems() == 0
    suspend fun saveItems(itemEntity: List<ItemEntity>) = dao.saveItem(itemEntity)
}