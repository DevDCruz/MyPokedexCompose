package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository


class FetchBackpackItemsUseCase(
    private val backPackItemRepository: IBackPackItemRepository
) {
    suspend operator fun invoke() = backPackItemRepository.fetchBackPackItems()
}