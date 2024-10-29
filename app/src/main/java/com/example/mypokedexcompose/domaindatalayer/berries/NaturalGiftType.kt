package com.example.mypokedexcompose.domaindatalayer.berries

import kotlinx.serialization.Serializable

@Serializable
data class NaturalGiftType(
    val name: String,
    val url: String
)