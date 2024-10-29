package com.example.mypokedexcompose.domaindatalayer.backpackItems

import kotlinx.serialization.Serializable

@Serializable
data class Generation(
    val name: String,
    val url: String
)