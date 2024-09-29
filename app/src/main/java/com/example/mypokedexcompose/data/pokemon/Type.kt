package com.example.mypokedexcompose.data.pokemon


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity
@Serializable
data class Type(
    @PrimaryKey(autoGenerate = true)
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: TypeX
)