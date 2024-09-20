package com.example.mypokedexcompose.data.items

import com.example.mypokedexcompose.data.dataSource.ItemRemoteDataSource

class ItemRepository(private val itemRemoteDataSource: ItemRemoteDataSource) {

    suspend fun fetchItems(): List<Item> = itemRemoteDataSource.fetchItems()

    suspend fun fetchItemById(id: Int): Item = itemRemoteDataSource.fetchItemById(id)


}