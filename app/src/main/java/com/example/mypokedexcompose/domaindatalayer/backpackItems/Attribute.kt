package com.example.mypokedexcompose.domaindatalayer.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class Attribute(
    val name: String,
    val url: String
)