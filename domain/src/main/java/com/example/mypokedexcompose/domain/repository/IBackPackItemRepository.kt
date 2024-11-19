package com.example.mypokedexcompose.domain.repository

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import kotlinx.coroutines.flow.Flow

interface IBackPackItemRepository {
    val backPackItems: Flow<List<BackpackItem>>

    suspend fun fetchBackPackItems()

    suspend fun fetchBackPackItemByName(name: String): Flow<BackpackItem?>
}