package com.example.mypokedexcompose.data.dataSource.remote.berry

import com.example.mypokedexcompose.domaindatalayer.berries.Firmness
import com.example.mypokedexcompose.domaindatalayer.berries.Flavor
import com.example.mypokedexcompose.domaindatalayer.berries.Item
import com.example.mypokedexcompose.domaindatalayer.berries.NaturalGiftType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BerryResult(
    val firmness: Firmness,
    val flavors: List<Flavor>,
    @SerialName("growth_time")
    val growthTime: Int,
    val id: Int,
    val item: Item,
    @SerialName("max_harvest")
    val maxHarvest: Int,
    val name: String,
    @SerialName("natural_gift_power")
    val naturalGiftPower: Int,
    @SerialName("natural_gift_type")
    val naturalGiftType: NaturalGiftType,
    val size: Int,
    val smoothness: Int,
    @SerialName("soil_dryness")
    val soilDryness: Int
)

