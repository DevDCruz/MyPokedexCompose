package com.example.mypokedexcompose.usecase


import com.example.mypokedexcompose.domain.backpackItems.BackpackItem
import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import kotlinx.coroutines.flow.Flow

class FetchBackPackItemByNameUseCase(
    private val backPackItemRepository: IBackPackItemRepository
) {
    suspend operator fun invoke(name: String): Flow<BackpackItem?> =
        backPackItemRepository.fetchBackPackItemByName(name)
}