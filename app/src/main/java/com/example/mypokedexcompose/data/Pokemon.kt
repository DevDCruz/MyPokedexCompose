package com.example.mypokedexcompose.data

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
    @SerialName("sprites")
    val sprites: String,
    @SerialName("spritePokedex")
    val spritePokedex: String,
)

