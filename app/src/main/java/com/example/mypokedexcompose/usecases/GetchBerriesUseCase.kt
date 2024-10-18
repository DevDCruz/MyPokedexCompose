package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.domain.berries.Berry
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import kotlinx.coroutines.flow.Flow

class GetchBerriesUseCase(
    private val berryRepository: BerryRepository
) {
    operator fun invoke(): Flow<List<Berry>> = berryRepository.berries
}