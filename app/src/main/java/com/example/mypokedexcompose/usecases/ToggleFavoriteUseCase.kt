package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.domaindatalayer.pokemon.Pokemon

class ToggleFavoriteUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) = pokemonRepository.toggleFavorite(pokemon)
}