package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import javax.inject.Inject

class FetchPokedexUseCase @Inject constructor(
    private val pokedexRepository: IPokedexRepository
) {
    suspend operator fun invoke() = pokedexRepository.fetchPokedexComplete()
}