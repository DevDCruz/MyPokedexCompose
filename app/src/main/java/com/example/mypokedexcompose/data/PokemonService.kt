package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.Sprites
import com.example.mypokedexcompose.data.home.PokedexResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokedex(@Query("limit") limit: Int): PokedexResult

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Pokemon


    @GET("sprites/pokemon/{id}.png")
    suspend fun getSpriteUrl(@Path("id") id: Int): Sprites
}