package com.example.mypokedexcompose.data.items

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemService {
    @GET("item")
    suspend fun fetchItems(@Query("limit") limit: Int): ItemsListResult

    @GET("item/{name}")
    suspend fun getItemByName(@Path("name") name: String): Item

    @GET("item/{id}")
    suspend fun getItemById(@Path("id") id: Int): Item
}