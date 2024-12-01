package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository

class ToggleFavoriteUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(pokemonDomain: PokemonDomain) = pokemonRepository.toggleFavorite(pokemonDomain)
}