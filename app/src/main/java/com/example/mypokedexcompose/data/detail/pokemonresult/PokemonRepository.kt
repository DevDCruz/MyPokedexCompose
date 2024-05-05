package com.example.mypokedexcompose.data.detail.pokemonresult

import com.example.mypokedexcompose.data.PokemonClient

class PokemonRepository {

    suspend fun fetchPokemon(name: String): PokemonResult = PokemonClient
        .instance
        .getPokemonByName(name)
}