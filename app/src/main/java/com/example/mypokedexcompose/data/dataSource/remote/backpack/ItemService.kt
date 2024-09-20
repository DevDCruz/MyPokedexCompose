package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.data.items.Item
import com.example.mypokedexcompose.data.items.ItemsListResult
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