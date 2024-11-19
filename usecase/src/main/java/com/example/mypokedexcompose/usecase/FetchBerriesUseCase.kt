package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository

class FetchBerriesUseCase(
    private val berryRepository: IBerryRepository
) {
    suspend operator fun invoke() = berryRepository.fetchBerries()
}