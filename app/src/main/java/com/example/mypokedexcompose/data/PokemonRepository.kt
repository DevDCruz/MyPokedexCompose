package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.home.pokemons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PokemonRepository {
    suspend fun fetchPokedex(): List<Pokemon> = withContext(Dispatchers.IO) {
        delay(2000)
       pokemons

    }
}