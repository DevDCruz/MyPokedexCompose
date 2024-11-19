package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import com.example.mypokedexcompose.domain.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class FetchPokemonByNameUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    operator fun invoke(name: String): Flow<Pokemon> = pokemonRepository.fetchPokemonByName(name)
}