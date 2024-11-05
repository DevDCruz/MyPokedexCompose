package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.PokedexRepository
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class GetchPokedexUseCase(private val pokedexRepository: PokedexRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = pokedexRepository.pokedex
}