package com.example.mypokedexcompose.data.dataSource.local.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import kotlinx.coroutines.flow.Flow

interface BackPackLocalDataSource {

    val items: Flow<List<BackpackItem>>

    fun getItemByName(name: String): Flow<BackpackItem?>

    suspend fun isNotFetched(): Boolean

    suspend fun saveItems(itemDomain: List<BackpackItem>)
}