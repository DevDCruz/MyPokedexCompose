package com.example.mypokedexcompose.data.items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResult(
    @SerialName("atributes")
    val attributes: List<Attribute>,
    @SerialName("baby_trigger_for")
    val babyTriggerFor: String,
    @SerialName("category")
    val category: Category,
    @SerialName("cost")
    val cost: Int,
    @SerialName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @SerialName("fling_effect")
    val flingEffect: String,
    @SerialName("fling_power")
    val flingPower: String,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerialName("held_by_pokemon")
    val heldByPokemon: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("machines")
    val machines: List<String>,
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<String>,
    @SerialName("sprites")
    val sprites: Sprites
)