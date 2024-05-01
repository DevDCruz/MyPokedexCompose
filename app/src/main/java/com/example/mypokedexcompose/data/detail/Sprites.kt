package com.example.mypokedexcompose.data.detail

import kotlinx.serialization.SerialName

data class Sprites(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any
)