package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import org.koin.core.annotation.Factory

@Factory
class FetchPokedexUseCase(
    private val pokedexRepository: IPokedexRepository
) {
    suspend operator fun invoke() = pokedexRepository.fetchPokedexComplete()
}