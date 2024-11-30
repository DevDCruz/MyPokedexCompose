package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import kotlinx.coroutines.flow.Flow

interface IBackPackItemRepository {
    val backPackItems: Flow<List<BackpackItemDomain>>

    suspend fun fetchBackPackItems()

    suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItemDomain?>
}