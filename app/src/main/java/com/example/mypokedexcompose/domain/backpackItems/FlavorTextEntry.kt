package com.example.mypokedexcompose.domain.backpackItems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntry(
    @SerialName("Language")
    val language: Language,
    @SerialName("text")
    val text: String,
    @SerialName("version_group")
    val versionGroup: VersionGroup
)