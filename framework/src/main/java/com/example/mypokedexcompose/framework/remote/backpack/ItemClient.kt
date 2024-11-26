package com.example.mypokedexcompose.framework.remote.backpack

import com.example.mypokedexcompose.framework.remote.ApiClient

object ItemClient {
    val instance: ItemService = ApiClient.createService(ItemService::class.java)
}