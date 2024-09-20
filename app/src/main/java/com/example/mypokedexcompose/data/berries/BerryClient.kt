package com.example.mypokedexcompose.data.berries

import com.example.mypokedexcompose.data.ApiClient

object BerryClient {
    val instance: BerryService = ApiClient.createService(BerryService::class.java)
}