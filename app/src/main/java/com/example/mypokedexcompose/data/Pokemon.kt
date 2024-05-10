package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.Type
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("name")
    val name: String,
    @SerialName("id")
    val id: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("types")
    val types: List<Type>?,
    @SerialName("sprites")
    val spriteDetail: String?,
    @SerialName("spritePokedex")
    val spritePokedex: String?,
)

