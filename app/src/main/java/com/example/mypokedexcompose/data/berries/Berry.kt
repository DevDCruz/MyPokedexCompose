package com.example.mypokedexcompose.data.berries

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Berry (
    @SerialName("firmness")
    val firmness: Firmness?,
    @SerialName("flavors")
    val flavors: List<Flavor>?,
    @SerialName("growth_time")
    val growthTime: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("item")
    val item: Item?,
    @SerialName("max_harvest")
    val maxHarvest: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("natural_gift_power")
    val naturalGiftPower: Int?,
    @SerialName("natural_gift_type")
    val naturalGiftType: NaturalGiftType?,
    @SerialName("size")
    val size: Int?,
    @SerialName("smoothness")
    val smoothness: Int?,
    @SerialName("soil_dryness")
    val soilDryness: Int?
)