package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.BackPpackItemRepository

class FetchBackpackItemsUseCase(
    private val backPpackItemRepository: BackPpackItemRepository
) {
    suspend operator fun invoke() = backPpackItemRepository.fetchBackPackItems()
}