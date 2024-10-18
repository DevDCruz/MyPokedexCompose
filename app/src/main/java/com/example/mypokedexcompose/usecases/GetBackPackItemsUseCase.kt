package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.data.dataSource.repository.BackPpackItemRepository
import kotlinx.coroutines.flow.Flow

class GetBackPackItemsUseCase(
    private val backPpackItemRepository: BackPpackItemRepository
) {
    operator fun invoke(): Flow<List<BackpackItem>> = backPpackItemRepository.backPackItems
}