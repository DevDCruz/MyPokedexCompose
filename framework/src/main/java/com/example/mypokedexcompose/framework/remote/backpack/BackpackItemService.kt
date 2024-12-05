package com.example.mypokedexcompose.framework.remote.backpack

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BackpackItemService {
    @GET("item")
    suspend fun fetchItems(@Query("limit") limit: Int): ItemsListResult

    @GET("item/{name}")
    suspend fun getItemByName(@Path("name") name: String): ItemResult
}