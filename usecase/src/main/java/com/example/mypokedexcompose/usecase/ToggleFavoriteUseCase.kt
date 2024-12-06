package com.example.mypokedexcompose.usecase

import com.example.mypokedexcompose.domain.pokemon.PokemonDomain
import com.example.mypokedexcompose.domain.repository.IPokemonRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepository
) {
    suspend operator fun invoke(pokemonDomain: PokemonDomain) =
        pokemonRepository.toggleFavorite(pokemonDomain)
}