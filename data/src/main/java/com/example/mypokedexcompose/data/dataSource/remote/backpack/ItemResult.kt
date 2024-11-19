package com.example.mypokedexcompose.data.dataSource.remote.backpack

import com.example.mypokedexcompose.domain.backpackItems.Attribute
import com.example.mypokedexcompose.domain.backpackItems.Category
import com.example.mypokedexcompose.domain.backpackItems.EffectEntry
import com.example.mypokedexcompose.domain.backpackItems.FlavorTextEntry
import com.example.mypokedexcompose.domain.backpackItems.GameIndice
import com.example.mypokedexcompose.domain.backpackItems.Sprites
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResult(
    val attributes: List<Attribute>,
    @SerialName("baby_trigger_for")
    val babyTriggerFor: String,
    val category: Category,
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
    val id: Int,
    val machines: List<String>,
    val name: String,
    val names: List<String>,
    val sprites: Sprites
)