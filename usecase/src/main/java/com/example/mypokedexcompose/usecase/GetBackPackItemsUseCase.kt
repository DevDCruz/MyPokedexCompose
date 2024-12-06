package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.backpackItems.BackpackItemDomain
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBackPackItemsUseCase @Inject constructor(
    private val backPackItemRepository: IBackPackItemRepository
) {
    operator fun invoke(): Flow<List<BackpackItemDomain>> = backPackItemRepository.backPackItems
}