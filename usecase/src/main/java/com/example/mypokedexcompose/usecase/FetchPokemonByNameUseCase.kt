package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonByNameUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    operator fun invoke(name: String): Flow<PokemonDomain> = pokemonRepository.fetchPokemonByName(name)
}