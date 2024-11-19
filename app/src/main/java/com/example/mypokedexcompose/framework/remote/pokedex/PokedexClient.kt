package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.framework.remote.ApiClient

object PokedexClient {
    val instance: PokedexService = ApiClient.createService(PokedexService::class.java)
}