package com.example.mypokedexcompose.framework.remote.pokemon

import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonResult

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonResult

}