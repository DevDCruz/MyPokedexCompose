package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository

class FetchPokedexUseCase(
    private val pokedexRepository: IPokedexRepository
) {
    suspend operator fun invoke() = pokedexRepository.fetchPokedexComplete()
}