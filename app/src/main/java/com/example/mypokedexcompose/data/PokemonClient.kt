package com.example.mypokedexcompose.data

import retrofit2.Retrofit
import retrofit2.create

object PokemonClient {
    val instance = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()
        .create<PokemonService>()
}