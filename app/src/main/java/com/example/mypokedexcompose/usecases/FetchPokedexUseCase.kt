package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository

class FetchPokedexUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke() = pokedexRepository.fetchPokedexComplete()
}