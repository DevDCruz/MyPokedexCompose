package com.example.mypokedexcompose.data.pokedex

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object PokedexClient {

    private val okHttpClient = OkHttpClient.Builder().build()

    val instance = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<PokedexService>()
}