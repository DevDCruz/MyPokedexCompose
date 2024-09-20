package com.example.mypokedexcompose.data.items

import com.example.mypokedexcompose.data.ApiClient

object ItemClient {
    val instance: ItemService = ApiClient.createService(ItemService::class.java)
}