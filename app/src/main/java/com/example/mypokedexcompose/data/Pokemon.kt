package com.example.mypokedexcompose.data

import com.example.mypokedexcompose.data.detail.pokemonresult.spritesfolder.Sprites
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
    val sprites: Sprites?,
    @SerialName("spritePokedex")
    val spritePokedex: String,
)

