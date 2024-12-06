package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokedexUseCase @Inject constructor(private val pokedexRepository: IPokedexRepository) {
    operator fun invoke(): Flow<List<PokemonDomain>> = pokedexRepository.pokedex
}