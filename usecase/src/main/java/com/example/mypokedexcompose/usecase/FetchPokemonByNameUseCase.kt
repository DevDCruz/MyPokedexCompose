package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class FetchPokemonByNameUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    operator fun invoke(name: String): Flow<PokemonDomain> = pokemonRepository.fetchPokemonByName(name)
}