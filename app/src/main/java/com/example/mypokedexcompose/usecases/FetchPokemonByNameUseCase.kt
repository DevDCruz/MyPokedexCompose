package com.example.mypokedexcompose.usecases

import com.example.mypokedexcompose.data.dataSource.repository.PokemonRepository
import com.example.mypokedexcompose.domaindatalayer.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class FetchPokemonByNameUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Pokemon> = pokemonRepository.fetchPokemonByName(name)
}