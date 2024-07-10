package com.example.mypokedexcompose.data.berries

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object BerryClient {

    private val okHttpClient = OkHttpClient.Builder().build()

    val instance = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<BerryService>()
}