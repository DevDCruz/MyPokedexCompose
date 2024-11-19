package com.example.mypokedexcompose.data.dataSource.local.backpack

import kotlinx.coroutines.flow.Flow

interface BackPackLocalDataSource {

    val items: Flow<List<ItemEntity>>

    fun getItemByName(name: String): Flow<ItemEntity?>

    suspend fun isEmpty(): Boolean

    suspend fun saveItems(itemEntity: List<ItemEntity>)
}