package com.example.mypokedexcompose.framework.remote.pokemon

import com.example.mypokedexcompose.framework.remote.ApiClient


object PokemonClient {
    val instance: PokemonService = ApiClient.createService(PokemonService::class.java)
}