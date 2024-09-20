package com.example.mypokedexcompose.data.dataSource.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.ApiClient

object PokedexClient {
    val instance: PokedexService = ApiClient.createService(PokedexService::class.java)
}