package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository

class FetchBerriesUseCase(
    private val berryRepository: BerryRepository
) {
    suspend operator fun invoke() = berryRepository.fetchBerries()
}