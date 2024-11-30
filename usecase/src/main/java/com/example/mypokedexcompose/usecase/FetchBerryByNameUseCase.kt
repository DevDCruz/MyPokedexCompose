package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.Flow

class FetchBerryByNameUseCase(
    private val berryRepository: IBerryRepository
) {
    suspend operator fun invoke(name: String): Flow<BerryDomain?> = berryRepository.fetchBerryByName(name)
}