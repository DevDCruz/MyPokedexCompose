package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import org.koin.core.annotation.Factory

@Factory
class FetchBerriesUseCase(
    private val berryRepository: IBerryRepository
) {
    suspend operator fun invoke() = berryRepository.fetchBerries()
}