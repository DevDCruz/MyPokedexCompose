package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import org.koin.core.annotation.Factory

@Factory
class ToggleFavoriteUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(pokemonDomain: PokemonDomain) = pokemonRepository.toggleFavorite(pokemonDomain)
}