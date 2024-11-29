package com.example.mypokedexcompose.framework.database.backpack


import com.example.mypokedexcompose.data.dataSource.local.backpack.BackPackLocalDataSource
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BackPackRoomDataSource(
    private val dao: BackPackDao,
    private val itemsMapper: ItemsMapper
) :
    BackPackLocalDataSource {

    override val items =
        dao.fetchItems().map { items -> itemsMapper.fromEntityListToDomainList(items) }

    override fun getItemByName(name: String): Flow<BackpackItem?> {
        return dao.fetchItemByName(name).map { itemEntity ->
            itemEntity?.let { itemsMapper.fromEntityToDomain(it) }
        }
    }

    override suspend fun isNotFetched() = dao.countItems() < 10

    override suspend fun saveItems(itemDomain: List<BackpackItem>) {
        val items = itemsMapper.fromDomainListToEntityList(itemDomain)
        dao.saveItem(items)
    }
}