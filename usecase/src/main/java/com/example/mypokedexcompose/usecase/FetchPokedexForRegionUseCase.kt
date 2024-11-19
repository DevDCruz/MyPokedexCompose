package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository

class FetchPokedexForRegionUseCase(
    private val pokedexRepository: IPokedexRepository
) {
    suspend operator fun invoke(range: IntArray) =
        pokedexRepository.fetchRegionalPokedex(offset = range[0], limit = range[1] - range[0])
}