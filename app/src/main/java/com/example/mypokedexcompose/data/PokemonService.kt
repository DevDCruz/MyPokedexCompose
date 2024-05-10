package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.PokemonResult
import com.example.mypokedexcompose.data.home.PokedexResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokedex(@Query("limit") limit: Int): PokedexResult

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonResult

}