package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository

class FetchPokedexForRegionUseCase(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke(range: IntArray) = pokedexRepository.fetchRegionalPokedex(offset = range[0], limit = range[1] - range[0])
}