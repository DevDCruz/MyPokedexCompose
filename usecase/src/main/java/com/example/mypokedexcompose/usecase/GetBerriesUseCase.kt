package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBerriesUseCase @Inject constructor(
    private val berryRepository: IBerryRepository
) {
    operator fun invoke(): Flow<List<BerryDomain>> = berryRepository.berries
}