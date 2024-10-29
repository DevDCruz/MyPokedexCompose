package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.domaindatalayer.backpackItems.BackpackItem
import com.example.mypokedexcompose.data.dataSource.repository.BackPpackItemRepository
import kotlinx.coroutines.flow.Flow

class FetchBackPackItemByNameUseCase(
    private val backPpackItemRepository: BackPpackItemRepository
) {
    suspend operator fun invoke(name: String): Flow<BackpackItem?> =
        backPpackItemRepository.fetchBackPackItemByName(name)
}