package com.example.mypokedexcompose.domain.berries

import kotlinx.serialization.Serializable

@Serializable
data class Flavor(
    val flavor: FlavorX,
    val potency: Int
)