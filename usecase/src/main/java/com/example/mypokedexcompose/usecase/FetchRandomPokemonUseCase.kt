package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchRandomPokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(): Flow<PokemonDomain?> = pokemonRepository.fetchRandomPokemon()
}