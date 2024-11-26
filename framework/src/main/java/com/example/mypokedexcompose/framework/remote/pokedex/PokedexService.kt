package com.example.mypokedexcompose.framework.remote.pokedex

import com.example.mypokedexcompose.data.dataSource.remote.pokedex.PokedexResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fectkRegionalPokedex(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokedexResult
}