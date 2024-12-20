package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import org.koin.core.annotation.Factory


@Factory
class FetchBackpackItemsUseCase(
    private val backPackItemRepository: IBackPackItemRepository
) {
    suspend operator fun invoke() = backPackItemRepository.fetchBackPackItems()
}