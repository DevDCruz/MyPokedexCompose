package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import com.example.mypokedexcompose.domain.berries.BerryDomain
import kotlinx.coroutines.flow.Flow

class GetBerriesUseCase(
    private val berryRepository: IBerryRepository
) {
    operator fun invoke(): Flow<List<BerryDomain>> = berryRepository.berries
}