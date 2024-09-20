package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.data.dataSource.remote.ApiClient

object ItemClient {
    val instance: ItemService = ApiClient.createService(ItemService::class.java)
}