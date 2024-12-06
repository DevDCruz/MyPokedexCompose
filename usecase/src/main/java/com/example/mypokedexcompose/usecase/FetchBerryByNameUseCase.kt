package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.berries.BerryDomain
import com.example.mypokedexcompose.domain.repository.IBerryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBerryByNameUseCase @Inject constructor(
    private val berryRepository: IBerryRepository
) {
    suspend operator fun invoke(name: String): Flow<BerryDomain?> = berryRepository.fetchBerryByName(name)
}