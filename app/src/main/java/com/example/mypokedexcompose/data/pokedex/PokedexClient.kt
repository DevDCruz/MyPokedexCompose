package com.example.mypokedexcompose.data.pokedex

import com.example.mypokedexcompose.data.ApiClient

object PokedexClient {
    val instance: PokedexService = ApiClient.createService(PokedexService::class.java)
}