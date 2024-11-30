package com.example.mypokedexcompose.data.dataSource.local.backpack

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import kotlinx.coroutines.flow.Flow

interface BackPackLocalDataSource {

    val items: Flow<List<BackpackItemDomain>>

    fun getItemByName(name: String): Flow<BackpackItemDomain?>

    suspend fun isNotFetched(): Boolean

    suspend fun saveItems(itemDomain: List<BackpackItemDomain>)
}