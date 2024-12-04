package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow

class GetPokedexUseCase(private val pokedexRepository: IPokedexRepository) {
    operator fun invoke(): Flow<List<PokemonDomain>> = pokedexRepository.pokedex
}