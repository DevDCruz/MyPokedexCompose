package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import com.example.mypokedexcompose.domain.berries.BerryDomain
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetBerriesUseCase(
    private val berryRepository: IBerryRepository
) {
    operator fun invoke(): Flow<List<BerryDomain>> = berryRepository.berries
}