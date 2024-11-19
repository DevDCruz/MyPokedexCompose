package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import kotlinx.coroutines.flow.Flow

class GetBackPackItemsUseCase(
    private val backPackItemRepository: IBackPackItemRepository
) {
    operator fun invoke(): Flow<List<BackpackItem>> = backPackItemRepository.backPackItems
}