package com.example.mypokedexcompose.data.dataSource.remote.berry

import com.example.mypokedexcompose.data.dataSource.remote.ApiClient

object BerryClient {
    val instance: BerryService = ApiClient.createService(BerryService::class.java)
}