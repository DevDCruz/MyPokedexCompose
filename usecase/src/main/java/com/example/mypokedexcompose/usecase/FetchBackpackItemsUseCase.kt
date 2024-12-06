package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBackPackItemRepository
import javax.inject.Inject

class FetchBackpackItemsUseCase @Inject constructor(
    private val backPackItemRepository: IBackPackItemRepository
) {
    suspend operator fun invoke() = backPackItemRepository.fetchBackPackItems()
}