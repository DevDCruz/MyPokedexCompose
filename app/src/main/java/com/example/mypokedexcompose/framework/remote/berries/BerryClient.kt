package com.example.mypokedexcompose.framework.remote.berries

import com.example.mypokedexcompose.framework.remote.ApiClient

object BerryClient {
    val instance: BerryService = ApiClient.createService(BerryService::class.java)
}