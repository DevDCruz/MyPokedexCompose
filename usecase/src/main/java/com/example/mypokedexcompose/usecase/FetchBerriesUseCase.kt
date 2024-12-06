package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import javax.inject.Inject

class FetchBerriesUseCase @Inject constructor(
    private val berryRepository: IBerryRepository
) {
    suspend operator fun invoke() = berryRepository.fetchBerries()
}