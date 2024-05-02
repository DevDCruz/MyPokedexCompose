package com.example.mypokedexcompose.data

interface PokemonService {

    suspend fun fetchPokedex(): List<Pokemon>
}