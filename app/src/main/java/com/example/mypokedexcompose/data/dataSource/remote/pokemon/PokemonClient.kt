package com.example.mypokedexcompose.data.dataSource.remote.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.ApiClient


object PokemonClient {
    val instance: PokemonService = ApiClient.createService(PokemonService::class.java)
}