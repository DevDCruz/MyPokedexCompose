package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class GetchPokedexUseCase(private val pokedexRepository: IPokedexRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = pokedexRepository.pokedex
}