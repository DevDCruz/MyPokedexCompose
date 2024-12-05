package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetBackPackItemsUseCase(
    private val backPackItemRepository: IBackPackItemRepository
) {
    operator fun invoke(): Flow<List<BackpackItemDomain>> = backPackItemRepository.backPackItems
}