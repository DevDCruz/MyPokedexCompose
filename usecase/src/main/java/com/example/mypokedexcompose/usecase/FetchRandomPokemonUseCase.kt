package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class FetchRandomPokemonUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(): Flow<PokemonDomain?> = pokemonRepository.fetchRandomPokemon()
}