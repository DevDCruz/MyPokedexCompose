package com.example.mypokedexcompose.data.pokedex


import com.example.mypokedexcompose.data.dataSource.remote.pokemon.PokemonResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResult(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<PokemonResult>
)





