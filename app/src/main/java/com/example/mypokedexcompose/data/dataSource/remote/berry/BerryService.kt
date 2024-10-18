package com.example.mypokedexcompose.data.dataSource.remote.berry


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BerryService {
    @GET("berry")
    suspend fun fetchBerries(@Query("limit") limit: Int): BerriesListResult

    @GET("berry/{name}")
    suspend fun getBerryByName(@Path("name") name: String): BerryResult
}