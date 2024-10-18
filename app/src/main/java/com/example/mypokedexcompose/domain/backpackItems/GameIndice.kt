package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndice(
    @SerialName("game_index")
    val gameIndex: Int,
    @SerialName("generation")
    val generation: Generation
)