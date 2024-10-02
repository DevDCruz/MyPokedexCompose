package com.example.mypokedexcompose.data.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("types")
    val types: List<Type>?,
    @SerialName("favorite")
    var favorite: Boolean
)

