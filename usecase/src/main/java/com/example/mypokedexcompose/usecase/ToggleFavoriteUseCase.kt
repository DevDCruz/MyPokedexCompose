package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.Pokemon
import com.example.mypokedexcompose.domain.repository.IPokemonRepository

class ToggleFavoriteUseCase(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) = pokemonRepository.toggleFavorite(pokemon)
}