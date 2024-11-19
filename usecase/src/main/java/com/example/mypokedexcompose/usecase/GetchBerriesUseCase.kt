package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IBerryRepository
import com.example.mypokedexcompose.domain.berries.Berry
import kotlinx.coroutines.flow.Flow

class GetchBerriesUseCase(
    private val berryRepository: IBerryRepository
) {
    operator fun invoke(): Flow<List<Berry>> = berryRepository.berries
}