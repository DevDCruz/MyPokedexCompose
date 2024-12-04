package com.example.mypokedexcompose.framework.remote.backpack

import com.example.mypokedexcompose.framework.remote.ApiClient

object BackPackItemClient {
    val instance: ItemService = ApiClient.createService(ItemService::class.java)
}