package com.example.mypokedexcompose.data.dataSource.remote.berry


import com.example.mypokedexcompose.data.berries.BerriesResult
import com.example.mypokedexcompose.data.berries.Berry
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BerryService {
    @GET("berry")
    suspend fun fetchBerries(@Query("limit") limit: Int): BerriesResult

    @GET("berry/{name}")
    suspend fun getBerryByName(@Path("name") name: String): Berry

    @GET("berry/{id}")
    suspend fun geBerryById(@Path("id") id: Int): BerriesResult
}