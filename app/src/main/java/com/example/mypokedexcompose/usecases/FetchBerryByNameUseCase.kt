package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.domaindatalayer.berries.Berry
import com.example.mypokedexcompose.data.dataSource.repository.BerryRepository
import kotlinx.coroutines.flow.Flow

class FetchBerryByNameUseCase(
    private val berryRepository: BerryRepository
) {
    suspend operator fun invoke(name: String): Flow<Berry?> = berryRepository.fetchBerryByName(name)
}