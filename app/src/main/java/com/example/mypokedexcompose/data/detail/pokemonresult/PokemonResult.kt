package com.example.mypokedexcompose.data.detail.pokemonresult


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResult(
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("types")
    val types: List<Type>,
    @SerialName("weight")
    val weight: Int
)