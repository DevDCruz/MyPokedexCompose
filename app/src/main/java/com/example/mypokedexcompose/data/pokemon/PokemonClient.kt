package com.example.mypokedexcompose.data.pokemon

import com.example.mypokedexcompose.data.ApiClient


object PokemonClient {
    val instance: PokemonService = ApiClient.createService(PokemonService::class.java)
}